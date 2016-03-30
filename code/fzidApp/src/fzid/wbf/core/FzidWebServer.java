package fzid.wbf.core;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.NCSARequestLog;
import org.eclipse.jetty.server.NetworkConnector;
import org.eclipse.jetty.server.RequestLog;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.RequestLogHandler;

import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.eclipse.jetty.util.thread.ThreadPool;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.xml.XmlConfiguration;


public class FzidWebServer {
	
	private static final String LOG_PATH = "./var/logs/access/yyyy_mm_dd.request.log";
	
	private static final String WEB_XML = "META-INF/webapp/WEB-INF/web.xml";
	private static final String PROJECT_RELATIVE_PATH_TO_WEBAPP = "WebContent";
	
	private Server server;
	private int port;
    private String bindInterface;
	public FzidWebServer(int aPort){
		this(aPort, null);
	}
	public FzidWebServer(int aPort,String aBindInterface){
		port=aPort;
		bindInterface=aBindInterface;
	}
	public void start() throws Exception {
		
		server = new Server(createThreadPool());
		//server.setThreadPool(createThreadPool());
        server.addConnector(createConnector());
        server.setHandler(createHandlers());        
        server.setStopAtShutdown(true);
                
        server.start();
	}
	public void join() throws InterruptedException {
        server.join();
    }
	private ThreadPool createThreadPool(){
		QueuedThreadPool threadPool = new QueuedThreadPool();
        threadPool.setMinThreads(10);
        threadPool.setMaxThreads(100);
        return threadPool;
	}
//	private SelectChannelConnector createConnector() {
//		SelectChannelConnector connector = new SelectChannelConnector();
//        connector.setPort(port);
//        connector.setHost(bindInterface);
//        return connector;
//    }
	
	private NetworkConnector createConnector() {
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(port);
        connector.setHost(bindInterface);
        return connector;
    }
	private HandlerCollection createHandlers() {
		WebAppContext _ctx = new WebAppContext();
        _ctx.setContextPath("/");
        
                  
        _ctx.setWar(PROJECT_RELATIVE_PATH_TO_WEBAPP);
        
        
        List<Handler> _handlers = new ArrayList<Handler>();
        
        _handlers.add(_ctx);
        
        HandlerList _contexts = new HandlerList();
        _contexts.setHandlers(_handlers.toArray(new Handler[0]));
        
        RequestLogHandler _log = new RequestLogHandler();
        _log.setRequestLog(createRequestLog());
        
        HandlerCollection _result = new HandlerCollection();
        _result.setHandlers(new Handler[] {_contexts, _log});
        
        return _result;
    }
	private RequestLog createRequestLog() {
        //记录访问日志的处理
        NCSARequestLog requestLog = new NCSARequestLog();
        requestLog.setFilename("logs" + "/yyyy-mm-dd.log");
        requestLog.setRetainDays(90);
        requestLog.setExtended(false);
        requestLog.setAppend(true);
        //requestLog.setLogTimeZone("GMT");
        requestLog.setLogTimeZone("Asia/Shanghai");
        requestLog.setLogDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        requestLog.setLogLatency(true);
        return requestLog;
    }
	
	private URL getResource(String aResource)
    {
        return Thread.currentThread().getContextClassLoader().getResource(aResource); 
    }
    
    private String getShadedWarUrl()
    {
        String _urlStr = getResource(WEB_XML).toString();
        // Strip off "WEB-INF/web.xml"
        return _urlStr.substring(0, _urlStr.length() - 15);
    }
}
