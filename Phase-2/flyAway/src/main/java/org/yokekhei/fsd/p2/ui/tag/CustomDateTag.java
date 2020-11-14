package org.yokekhei.fsd.p2.ui.tag;

import java.io.IOException;
import java.io.StringWriter;
import java.time.format.DateTimeFormatter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.yokekhei.fsd.p2.Common;

public class CustomDateTag extends SimpleTagSupport {
	
	private String format;
	
	@Override
	public void doTag() throws JspException, IOException {
		StringWriter sw = new StringWriter();
		getJspBody().invoke(sw);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		getJspContext().getOut().print(Common.toLocalDate(sw.toString(), "yyyy-MM-dd").format(formatter));
	}

	public void setFormat(String format) {
		this.format = format;
	}
	
}
