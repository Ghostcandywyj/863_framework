package workflow.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.JSONArray;
import org.json.JSONObject;

import workflow.db.Focusws;
import workflow.db.FocuswsDAO;
import workflow.db.Rating;
import workflow.db.RatingDAO;
import workflow.db.Webservices;
import workflow.db.WebservicesDAO;
import workflow.parser.WSDLazyParser;

@SuppressWarnings("unchecked")
public class ServiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public ServiceServlet() {
        super();
    }
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		resp.setHeader("Access-Control-Allow-Origin", "*");
		String query=req.getParameter("query");
		Long loginId=(Long)req.getSession().getAttribute("userid");
//		if(loginId==null){
//			resp.getWriter().append(ServletConstants.SESSION_TIMEOUT_ERROR);
//			return;
//		}
		 if(query.equals("getServiceName")){
			long wsid=Long.parseLong(req.getParameter("wsid"));
			resp.getWriter().append(getWsNameById(wsid));
		}else if(query.equals("getService")){
			long wsid=Long.parseLong(req.getParameter("wsid"));
			resp.getWriter().append(getWebServiceById(wsid));
		}else if(query.equals("searchService")){
			String name=req.getParameter("name");
			resp.getWriter().append(searchWebService(name));
		}
		else if(query.equals("getFocusedService")){
			long uid=Long.parseLong(req.getParameter("uid"));
			resp.getWriter().append(getFocusedWS(uid));
		}else if(query.equals("getServiceInfo")){
			long wsId=Long.parseLong(req.getParameter("wsid"));
			resp.getWriter().append(getWSInfo(wsId));
		}else if(query.equals("getAllService")){
			int size=Integer.parseInt(req.getParameter("size"));
			int offset=Integer.parseInt(req.getParameter("offset"));
			resp.getWriter().append(getAllWS(offset,size));
		}else if(query.equals("addFocusService")){
			long uid=Long.parseLong(req.getParameter("uid"));
			long wsId=Long.parseLong(req.getParameter("wsid"));
			resp.getWriter().append(addFocusService(uid,wsId));
		}else if(query.equals("removeFocusService")){
			long uid=Long.parseLong(req.getParameter("uid"));
			long wsId=Long.parseLong(req.getParameter("wsid"));
			resp.getWriter().append(removeFocusService(uid,wsId));
		}else if(query.equals("getRating")){
			long wsId=Long.parseLong(req.getParameter("wsid"));
			resp.getWriter().append(getRating(wsId));
		}else if(query.equals("updateRating")){
			long uid=Long.parseLong(req.getParameter("uid"));
			long wsId=Long.parseLong(req.getParameter("wsid"));
			float rateValue=Float.parseFloat(req.getParameter("ratevalue"));
			resp.getWriter().append(updateRating(uid,wsId,rateValue));
		}else if(query.equals("uploadService")){
			
		}
		else{
			resp.getWriter().append(ServletConstants.UNKOWN_REQUEST_ERROR);
		}
		 resp.getWriter().flush();
		 resp.getWriter().close();
		
	}
	
	private String searchWebService(String name) {
		WebservicesDAO wsDAO=new WebservicesDAO();
		List<?> result=wsDAO.searchService(name);
		JSONArray jsonArray = new JSONArray(result);
		return jsonArray.toString();
	}
	private String getWebServiceById(long wsid) {
		WebservicesDAO wsDAO=new WebservicesDAO();
		Webservices ws=wsDAO.findById(wsid);
		JSONObject json=new JSONObject(ws);
		return json.toString();
	}
	private String getAllWS(int offset, int size) {
		WebservicesDAO wsDAO=new WebservicesDAO();
		List<Webservices> result=wsDAO.getAllWS(size, offset);
		JSONArray jsonArray = new JSONArray(result);
		return jsonArray.toString();
	}

	private String getWSInfo(long wsId) {
		WSDLazyParser parser=new WSDLazyParser(getServletContext());
		WebservicesDAO wsDAO=new WebservicesDAO();
		Webservices ws=wsDAO.findById(wsId);
		return parser.lazyParse(ws);
		
	}
	
	
	private String getWsNameById(long wsid) {
		WebservicesDAO wsDAO=new WebservicesDAO();
		Webservices ws=wsDAO.findById(wsid);
		return ws.getWsName();
	}

	private String getFocusedWS(long uid) {
		FocuswsDAO fDAO=new FocuswsDAO();
		WebservicesDAO wsDAO=new WebservicesDAO();
		List<Focusws> result=fDAO.findByUserId(uid);
		List<Webservices> wsList=new ArrayList<Webservices>();
		for(Focusws fws: result){
			Webservices ws=wsDAO.findById(fws.getWsid());
			if(ws!=null)
				wsList.add(ws);
		}
		JSONArray jsonArray = new JSONArray(wsList);
		return jsonArray.toString();
	}
	private String removeFocusService(long uid, long wsId) {
		FocuswsDAO fDAO=new FocuswsDAO();	
		try{
			List<Focusws> fws=fDAO.findByUserIdAndWsId(uid, wsId);
		
			if(fws.size()!=0){
				Focusws item=fws.get(0);
				Session session=fDAO.getSession();
				Transaction tx=null;
				try{
					tx=session.beginTransaction();
					session.delete(item);
					tx.commit();
				}catch(Exception e){
					e.printStackTrace();
				    throw e;
				}finally{
					
				     session.close( );	// 关闭session
				}
			}
		}catch(Exception e){
			return ServletConstants.ERROR_MSG;
		}
			
		
		return ServletConstants.SUCCESS_MSG;
	}

	private String addFocusService(long uid, long wsId) {
		FocuswsDAO fDAO=new FocuswsDAO();	
		try{
			
			List<Focusws> fws=fDAO.findByUserIdAndWsId(uid, wsId);
			if(fws.size()==0){
				Focusws item=new Focusws();
				item.setUserid(uid);
				item.setWsid(wsId);
				Session session=fDAO.getSession();
				Transaction tx=null;
				try{
					tx=session.beginTransaction();
					session.save(item);
					
					tx.commit();
				}catch(Exception e){
					e.printStackTrace();
				    throw e;
				}finally{
				     session.close( );	// 关闭session
				}
			}
		}catch(Exception e){
			return ServletConstants.ERROR_MSG;
		}
		return  ServletConstants.SUCCESS_MSG;
	}



	private String updateRating(long uid, long wsId, float rateValue) {
		RatingDAO rDAO=new RatingDAO();
		try{
			List<Rating> ratings=rDAO.findByUserAndWsId(uid, wsId);
			Rating rating=null;
			if(ratings.size()==0){
				rating=new Rating();
				rating.setUserId(uid);
				rating.setWsId(wsId);
			
			}else{
				rating=ratings.get(0);
			}
			rating.setRateValue(rateValue);
			Session session=rDAO.getSession();
			Transaction tx=null;
			try{
				tx=session.beginTransaction();
				session.save(rating);
				tx.commit();
			}catch(Exception e){
				e.printStackTrace();
			    throw e;
			}finally{
			     session.close( );	// 关闭session
			}
		}catch(Exception e){
			return  ServletConstants.ERROR_MSG;
		}
		return  ServletConstants.SUCCESS_MSG;
	}

	private String getRating(long wsId) {
		RatingDAO rDAO=new RatingDAO();
		List<Rating> result=rDAO.findByWsId(wsId);
		JSONArray jsonArray = new JSONArray(result);
		return jsonArray.toString();

	}


}
