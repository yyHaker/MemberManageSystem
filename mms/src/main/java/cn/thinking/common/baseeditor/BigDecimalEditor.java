package cn.thinking.common.baseeditor;

import java.beans.PropertyEditorSupport;
import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;

public class BigDecimalEditor extends PropertyEditorSupport {
	public void setAsText(String text) throws IllegalArgumentException {  
        if (StringUtils.isEmpty(text)) {  
            // Treat empty String as null value.  
            setValue(null);  
        } else {  
            setValue(BigDecimal.valueOf(Long.parseLong(text.trim())));  
        }  
    }
}
