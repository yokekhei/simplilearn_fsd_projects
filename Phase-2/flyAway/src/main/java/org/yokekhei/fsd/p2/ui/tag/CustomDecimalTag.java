package org.yokekhei.fsd.p2.ui.tag;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.yokekhei.fsd.p2.Common;

public class CustomDecimalTag extends SimpleTagSupport {

	private int scale;

	@Override
	public void doTag() throws JspException, IOException {
		StringWriter sw = new StringWriter();
		getJspBody().invoke(sw);
		
		getJspContext().getOut().print(Common.roundBigDecimal(sw.toString(), scale));
	}
	
	public void setScale(int scale) {
		this.scale = scale;
	}
	
}
