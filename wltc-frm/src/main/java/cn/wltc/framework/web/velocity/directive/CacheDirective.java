package cn.wltc.framework.web.velocity.directive;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.exception.TemplateInitException;
import org.apache.velocity.runtime.RuntimeServices;
import org.apache.velocity.runtime.directive.Directive;
import org.apache.velocity.runtime.parser.node.Node;
import org.apache.velocity.runtime.parser.node.SimpleNode;

import cn.wltc.framework.util.cache.CompactCache;

/**
 * �� http://www.ibm.com/developerworks/cn/java/j-lo-velocity ���� �﷨Ϊ
 * #cache()...#end ���� #cache('key') ... #end <br/>
 * ����keyΪָ����cache key������ޣ�ϵͳ�Զ����һ��
 * 
 * @author fish
 */
public class CacheDirective extends Directive {

	private static final String CacheProviderKey = "direcitive.cache.provider";

	private static final String DevModeKey = "direcitive.cache.dev.mode";
	
	protected final Log logger = LogFactory.getLog(this.getClass());

	private CompactCache<String, String> cache;

	private boolean devMode = false;

	/**
	 * ָ������
	 */
	@Override
	public String getName() {
		return "cache";
	}

	/**
	 * ָ������Ϊ��ָ��
	 */
	@Override
	public int getType() {
		return BLOCK;
	}

	@Override
	public void init(RuntimeServices rs, InternalContextAdapter context,
			Node node) throws TemplateInitException {
		super.init(rs, context, node);
		Object obj = rs.getProperty(CacheProviderKey);
		if (obj == null) {
			throw new TemplateInitException(CacheProviderKey
					+ " not find in config.", "cache", 0, 0);
		}
		if (!(obj instanceof CompactCache<?, ?>)) {
			throw new TemplateInitException(CacheProviderKey
					+ " is not CompactCache.", "cache", 0, 0);
		}
		cache = (CompactCache) obj;
		devMode = rs.getBoolean(DevModeKey, false);
		if(logger.isDebugEnabled()){
			logger.debug("CacheDirective init end.cache.provider["+cache+"] devMode:"+this.devMode);
		}
	}

	@Override
	public boolean render(InternalContextAdapter context, Writer writer,
			Node node) throws IOException, ResourceNotFoundException,
			ParseErrorException, MethodInvocationException {
		if (this.devMode) {
			//����ģʽ,����cache
			Node bodyNode = getBodyNode(context, node);
			bodyNode.render(context, writer);
			return true;
		}
		String key = getCacheKey(context, node);
		String cache_html = this.cache.get(key);
		if (cache_html == null) {
			Node bodyNode = getBodyNode(context, node);
			StringWriter sw = new StringWriter();
			bodyNode.render(context, sw);
			cache_html = sw.toString();
			this.cache.put(key, cache_html);
		}
		writer.write(cache_html);
		return true;
	}

	protected String getCacheKey(InternalContextAdapter context, Node node) {
		if (node.jjtGetNumChildren() == 1) {// û��ָ��key���Զ����һ��
			StringBuilder sb = new StringBuilder();
			sb.append(context.getCurrentTemplateName()).append(':').append(
					node.getLine()).append(':').append(node.getColumn());
			return sb.toString();
		} else {
			SimpleNode sn_key = (SimpleNode) node.jjtGetChild(0);
			return (String) sn_key.value(context);
		}
	}

	protected Node getBodyNode(InternalContextAdapter context, Node node) {
		int children = node.jjtGetNumChildren();
		if (children == 1) {// û��ָ��key
			return node.jjtGetChild(0);
		} else {
			return node.jjtGetChild(children - 1);
		}
	}
}
