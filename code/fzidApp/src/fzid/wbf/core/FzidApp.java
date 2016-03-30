package fzid.wbf.core;
/**
 * App入口
 * @author fzid
 *
 */
public class FzidApp {
	public static final int PORT=8081;
	public static void main(String... anArgs) throws Exception {
		new FzidApp().start();
	}
	public FzidApp(){
		System.out.println("启动服务 "+PORT);
		websvr=new FzidWebServer(PORT);
	}
	public void start() throws Exception {
		websvr.start();
		websvr.join();
    }
	private FzidWebServer websvr=null;
}
