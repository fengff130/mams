package fzid.wbf.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fzid.wbf.pojo.Answer;
import fzid.wbf.pojo.AnswerForList;
import fzid.wbf.pojo.HarvesterInfo;
import fzid.wbf.pojo.MobileLogin;
import fzid.wbf.service.HarvesterService;

/**
 * 主页控制器
 * @author fzid
 *
 */
@RestController
public class HomeController {
	
	@Autowired
	private HttpServletRequest request; 
	
	@RequestMapping("/")
    public String home() {
		System.out.println("进到主页面");
        return "index";
    }
	
	@RequestMapping(value="/mobile/login")
    public Answer mobileLogin(@Valid MobileLogin ml) {
		//@RequestBody MobileLogin ml
		System.out.println(ml.toString());
        return new Answer(200,"登录成功");
    }
	
	@RequestMapping(value="/mobile/meterlist", produces = "text/html;charset=UTF-8")
    public String getMeterList() {
		//@RequestBody MobileLogin ml
		ObjectMapper mapper = new ObjectMapper(); 
		Map<String, Object> JSONParamsMap=new HashMap<String, Object>();
		JSONParamsMap.put("status", "成功");
		JSONParamsMap.put("Answer", new Answer(200,"登录成功"));
		String json="";
		try {
			json=mapper.writeValueAsString(JSONParamsMap);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(json);
		return json;
    }
	
	@RequestMapping(value="/mobile/uploadMeterPhoto", method = RequestMethod.GET)
	public Answer uploadMeterPhoto(@RequestParam("imgfile") MultipartFile file){
		String meterCode="123456789";
		String filePath = request.getSession().getServletContext().getRealPath("/") + "/meterPhotos/"+meterCode+"/";
		
		System.out.println(filePath);
		File photodir=new File(filePath);
		if(!photodir.exists()){
			photodir.mkdir();
			
		}
		String[] filetype=file.getContentType().split("/");
		String photofile=filePath+"/"+getDateTimeNoSplit()+"."+filetype[1];
		try {
			file.transferTo(new File(photofile));
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Answer(200,file.getContentType());
	}
	@RequestMapping(value="mobile/harvesters", method = RequestMethod.GET)
	public Answer getHarvester(){
		
		List<HarvesterInfo> hvrlist=hvrSrv.find();
		AnswerForList<HarvesterInfo> answer =new AnswerForList<HarvesterInfo>(hvrlist);
		return answer;
		
	}
	/**
	 * 实际格式 yyMMddHHmmss
	 * @return
	 */
	public static String getDateTimeNoSplit(){
		return getFormat("yyMMddHHmmss").format(new Date());
	}
	
	private static SimpleDateFormat getFormat(String str){
		SimpleDateFormat format =new SimpleDateFormat(str);
		format.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		return format;
	}
	@Autowired
	private HarvesterService hvrSrv;
}
