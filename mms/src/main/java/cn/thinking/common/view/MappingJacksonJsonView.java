/*
 * $Log: MappingJacksonJsonView.java,v $
 * Revision 1.1  2011/06/01 05:46:58  kkaiwen
 * 2011.6.1 create
 *
 */
package cn.thinking.common.view;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.view.AbstractView;

/**
 * 
 * Description: JSON对象视图解析类
 * 
 * Department: 沈阳开发二部 
 * @author liu.jia_neu
 * @update [修改人] [修改时间]
 * @version 1.0
 *
 */
public class MappingJacksonJsonView extends AbstractView {
    public static final String DEFAULT_CONTENT_TYPE = "application/json";

    private ObjectMapper objectMapper = new ObjectMapper();

    private JsonEncoding encoding = JsonEncoding.UTF8;

    private boolean prefixJson = false;

    private Set<String> renderedAttributes;

    public MappingJacksonJsonView() {
        setContentType("application/json");
    }

    public void setObjectMapper(ObjectMapper objectMapper) {
        Assert.notNull(objectMapper, "'objectMapper' must not be null");
        this.objectMapper = objectMapper;
    }

    public void setEncoding(JsonEncoding encoding) {
        Assert.notNull(encoding, "'encoding' must not be null");
        this.encoding = encoding;
    }

    public void setPrefixJson(boolean prefixJson) {
        this.prefixJson = prefixJson;
    }

    public void setRenderedAttributes(Set<String> renderedAttributes) {
        this.renderedAttributes = renderedAttributes;
    }

    protected void prepareResponse(HttpServletRequest request, HttpServletResponse response) {
        if (request.getAttribute("contentType") != null && !"".equals(request.getAttribute("contentType").toString().trim())) {
            response.setContentType(request.getAttribute("contentType").toString());
        }
        else {
            response.setContentType(getContentType());
        }
        response.setCharacterEncoding(this.encoding.getJavaName());
    }

    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Object value = filterModel(model);
        JsonGenerator generator = this.objectMapper.getJsonFactory().createJsonGenerator(response.getOutputStream(), this.encoding);
        if (this.prefixJson) {
            generator.writeRaw("{} && ");
        }
        this.objectMapper.writeValue(generator, value);
    }

    @SuppressWarnings("unchecked")
    protected Object filterModel(Map<String, Object> model) {
        Map result = new HashMap(model.size());
        Set renderedAttributes = (!(CollectionUtils.isEmpty(this.renderedAttributes))) ? this.renderedAttributes : model.keySet();
        for (Map.Entry entry : model.entrySet()) {
            if ((!(entry.getValue() instanceof BindingResult)) && (renderedAttributes.contains(entry.getKey()))) {
                result.put((String) entry.getKey(), entry.getValue());
            }
        }
        return result;
    }

}
