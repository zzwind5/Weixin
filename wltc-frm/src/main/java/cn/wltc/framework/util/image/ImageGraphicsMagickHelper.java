/**
 * 
 */
package cn.wltc.framework.util.image;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.im4java.core.ConvertCmd;
import org.im4java.core.GraphicsMagickCmd;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;
import org.im4java.process.ProcessStarter;

/**
 * 
 * <b>ͼƬ������</b><br/>
 * 	<p>&nbsp;&nbsp;&nbsp;&nbsp;��ͼƬ�������ǻ���graphicsMagick������й����ģ�����������û�а�װ
 * graphicsMagick������뵽http://www.graphicsmagick.org/download.html���ء�
 * ע�⣺Linux�汾������������Դ�����Ҫ�ֶ����벢��װ��<br/>
 * ���ೣ��ͼƬ�Ŀ��ļ����ص�ַ��http://www.imagemagick.org/download/delegates/<br/><br/></p>
 * <p>
 * graphicsMagick�����װ������<br/>
 * 		&nbsp;&nbsp;&nbsp;&nbsp;1����װrpm����ʵ�ǽ��rpm -Uvh GraphicsMagick-1.3.12-1.src.rpm<br/>
 * 		&nbsp;&nbsp;&nbsp;&nbsp;2���ҵ������GraphicsMagick-1.3.12.tar.bz2�ļ�<br/>
 * 		&nbsp;&nbsp;&nbsp;&nbsp;3���ٽ��tar -jxvf GraphicsMagick-1.3.12.tar.bz2<br/>
 * 		&nbsp;&nbsp;&nbsp;&nbsp;4��������tar -xvf GraphicsMagick-1.3.12.tar<br/>
 * 		&nbsp;&nbsp;&nbsp;&nbsp;5�����뵽GraphicsMagick-1.3.12Ŀ¼��cd GraphicsMagick-1.3.12/<br/>
 * 		&nbsp;&nbsp;&nbsp;&nbsp;6���������ã�./configure<br/>
 *		&nbsp;&nbsp;&nbsp;&nbsp;7�����룺make<br/>
 *		&nbsp;&nbsp;&nbsp;&nbsp;8����װ��make install<br/>
 *		&nbsp;&nbsp;&nbsp;&nbsp;9������ѡ��ж�أ�make uninstall<br/><br/>
 *</p>
 *<p>
 *������<br/>
 *		&nbsp;&nbsp;&nbsp;&nbsp;im4java-1.1.0.jar&nbsp;&nbsp;&nbsp;&nbsp;���ص�ַ��http://sourceforge.net/projects/im4java/files/ <br/><br/>
 * </p><p>
 *ʹ�÷�����<br/>
 *		&nbsp;&nbsp;�����Windows��ʹ��<br/>
 *		&nbsp;&nbsp;&nbsp;&nbsp;��һ�ַ�������Ҫ�ڻ�������path������GraphicsMagick��װ·����<br/>
 *		&nbsp;&nbsp;&nbsp;&nbsp;�ڶ��ַ�������ʹ�ñ���ǰ����setMagickPath(��װ·��)����<br/>
 *		&nbsp;&nbsp;Linuxû�й�ϵ�������ڰ�װ��ɺ�Ӧ��ִ�����gm �����Ƿ���</p>
 * 
 * @author sun.zhang
 * @date Sep 20, 2010 1:51:04 PM
 *	@version 1.0
 */
public class ImageGraphicsMagickHelper {
	
//	��Ҫ��������ˮӡͼƬ�ļ���ʶ
	private static boolean UPDATE_TEXT_WATERMARK_IMAGE = false;
//	�Ƿ��Ѿ�����������ˮӡͼƬ�ļ���ʶ
	private static boolean IS_UPDATED_TEXT_WATERMARK_IMAGE = false;
	
	private static String _font = null;
	private static String[] fontList = {"zysong.ttf","gkai00mp.ttf","gbsn00lp.ttf","simsun.ttc","MSYH.TTF"};
	private static String[] fontPathList = {"/usr/share/fonts/","/usr/share/fonts/zh_CN/","/usr/share/fonts/zh_CN/TrueType/"};
	private static String magickPath = null;
	
	/**
	 * �����ͼƬ��ָ����С
	 * @param srcPath		ԭͼƬ·��
	 * @param distPath		��ͼƬ·��
	 * @param width		��ͼƬ��Ȱٷֱ�[0-100]
	 * @param height	��ͼƬ�߶Ȱٷֱ�[0-100]
	 * @throws IOException		
	 * @throws InterruptedException
	 * @throws IM4JavaException
	 */
	public static void resizeProportion(String srcPath,String distPath,float width,float height) throws IOException, InterruptedException, IM4JavaException{
		File f = new File(srcPath);
		Image img = ImageIO.read(f);
		int imgWidth = img.getWidth(null);
		int imgHeight = img.getHeight(null);
		resize(srcPath, distPath, Math.round(imgWidth*(width/100)), Math.round(imgHeight*(height/100)));
	}
	
	/**
	 * �����ͼƬ��ָ����С
	 * @param srcPath		ԭͼƬ·��
	 * @param distPath		��ͼƬ·��
	 * @param width		��ͼƬ���
	 * @param height	��ͼƬ�߶�
	 * @throws IOException		
	 * @throws InterruptedException
	 * @throws IM4JavaException
	 */
	public synchronized static void resize(String srcPath,String distPath,int width,int height) throws IOException, InterruptedException, IM4JavaException{
		GraphicsMagickCmd  cmd = new GraphicsMagickCmd("convert");
		IMOperation op = new IMOperation();
		op.addImage(srcPath);
		op.resize(width,height);
		op.addImage(distPath);
		cmd.run(op);
	}
	
	/**
	 * ������ͼƬ��ָ����С������ӦĿ¼�����ͼƬ
	 * @param width		���
	 * @param height	�߶�
	 * @param srcPaths		���ͼƬ·��
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws IM4JavaException
	 */
	public synchronized static void resizeImages(int width,int height,String... srcPaths) throws IOException, InterruptedException, IM4JavaException {
	  for (String srcImage:srcPaths) {
	    int lastDot = srcImage.lastIndexOf('.');
	    String dstImage = srcImage.substring(1,lastDot-1)+"_new"+srcImage.substring(srcImage.lastIndexOf("."));
	    resize(srcImage,dstImage,width,height);
	  }
	}
	
	/**
	 * �����Ͻǿ�ʼ����ָ���߶ȿ�ȵ�ͼƬ
	 * @param srcPath		ԭͼƬ·��
	 * @param distPath		��ͼƬ·��
	 * @param width		���
	 * @param height	�߶�
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws IM4JavaException
	 */
	public static void cutOutImg(String srcPath, String distPath, int width, int height) throws IOException, InterruptedException, IM4JavaException{
		cutOutImg(srcPath, distPath, width, height, 0, 0);
	}
	
	/**
	 * ����������ָ���߶ȿ�ȵ�ͼƬ
	 * @param srcPath		ԭͼƬ·��
	 * @param distPath		��ͼƬ·��
	 * @param width		���
	 * @param height	�߶�
	 * @param x		X���
	 * @param y		Y���
	 * @throws IM4JavaException 
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	public synchronized static void cutOutImg(String srcPath, String distPath, int width, int height, int x, int y) throws IOException, InterruptedException, IM4JavaException{
		GraphicsMagickCmd  cmd = new GraphicsMagickCmd("convert");
		IMOperation op = new IMOperation();
		op.crop(width, height, x, y);
		op.addImage(srcPath);
		op.addImage(distPath);
		cmd.run(op);
	}
	
	/**
	 * ���Ź���λ�����ˮӡ
	 * 		Ĭ�ϱ߾�Ϊ10���أ�͸����Ϊ100��λ��Ϊ9��λ�����½ǣ�
	 * @param srcPath		ԭͼƬ·��
	 * @param distPath		��ͼƬ·��
	 * @param watermarkImg		ˮӡͼƬ·��
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws IM4JavaException
	 */
	public static void posWatermarkImg(String srcPath,String distPath,String watermarkImg) throws IOException, InterruptedException, IM4JavaException{
		int[] watermarkImgSide = getImageSide(watermarkImg);
		int[] srcImgSide = getImageSide(srcPath);
		int[] xy = getXY(srcImgSide, watermarkImgSide, 9, 10, false);
		watermarkImg(srcPath,distPath,watermarkImg,watermarkImgSide[0],watermarkImgSide[1],xy[0],xy[1],100);
	}
	
	/**
	 * ���Ź���λ�����ˮӡ
	 * @param srcPath		ԭͼƬ·��
	 * @param distPath		��ͼƬ·��
	 * @param watermarkImg		ˮӡͼƬ·��
	 * @param position		�Ź���λ��[1-9]
	 * @param margin 		�߾�
	 * @param alpha		͸����
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws IM4JavaException
	 */
	public static void posWatermarkImg(String srcPath,String distPath,String watermarkImg, int position, int margin, int alpha) throws IOException, InterruptedException, IM4JavaException{
		int[] watermarkImgSide = getImageSide(watermarkImg);
		int[] srcImgSide = getImageSide(srcPath);
		int[] xy = getXY(srcImgSide, watermarkImgSide, position, margin, false);
		watermarkImg(srcPath,distPath,watermarkImg,watermarkImgSide[0],watermarkImgSide[1],xy[0],xy[1],alpha);
	}
	
	/**
	 * ���ͼƬˮӡ
	 * @param srcPath		ԭͼƬ·��
	 * @param distPath		��ͼƬ·��
	 * @param watermarkImg		ˮӡͼƬ·��
	 * @param x		ˮӡ��ʼX���
	 * @param y		ˮӡ��ʼY���
	 * @param alpha		͸����[0-100]
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws IM4JavaException
	 */
	public static void watermarkImg(String srcPath,String distPath,String watermarkImg, int x, int y, int alpha) throws IOException, InterruptedException, IM4JavaException{
		int[] watermarkImgSide = getImageSide(watermarkImg);
		watermarkImg(srcPath,distPath,watermarkImg,watermarkImgSide[0],watermarkImgSide[1],x,y,alpha);
	}
	
	/**
	 * ���ͼƬˮӡ
	 * @param srcPath		ԭͼƬ·��
	 * @param distPath		��ͼƬ·��
	 * @param watermarkImg		ˮӡͼƬ·��
	 * @param width		ˮӡ��ȣ�������ˮӡͼƬ��С��ͬ��
	 * @param height	ˮӡ�߶ȣ�������ˮӡͼƬ��С��ͬ��
	 * @param x		ˮӡ��ʼX���
	 * @param y		ˮӡ��ʼY���
	 * @param alpha		͸����[0-100]
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws IM4JavaException
	 */
	public synchronized static void watermarkImg(String srcPath,String distPath,String watermarkImg, int width, int height, int x, int y, int alpha) throws IOException, InterruptedException, IM4JavaException{
		GraphicsMagickCmd  cmd = new GraphicsMagickCmd("composite");
		IMOperation op = new IMOperation();
		op.dissolve(alpha);
		op.geometry(width, height, x, y);
		op.addImage(watermarkImg);  
		op.addImage(srcPath);  
		op.addImage(distPath);  
		cmd.run(op);
	}
	
	/**
	 * ���վŹ���λ���������ˮӡ(Windows Only)
	 * 		Ĭ�����ã�λ�����½ǣ��߾�10���أ���͸������ɫΪ��ɫ
	 * @param srcPath		ԭͼƬ·��
	 * @param distPath		��ͼƬ·��
	 * @param textFile		ˮӡ����UTF-8�ı��ļ�·��
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws IM4JavaException
	 */
	@Deprecated
	public static void posWatermarkText(String srcPath,String distPath,String textFile) throws IOException, InterruptedException, IM4JavaException{
		posWatermarkText(srcPath, distPath, textFile, 20, 9, 10, "#000000", 100);
	}
	
	/**
	 * ���վŹ���λ���������ˮӡ��Windows Only��
	 * 		Ĭ�����ã�λ�����½ǣ��߾�10���أ���͸��
	 * @param srcPath		ԭͼƬ·��
	 * @param distPath		��ͼƬ·��
	 * @param textFile		ˮӡ����UTF-8�ı��ļ�·��
	 * @param fontsize		�����С
	 * @param color		��ɫ
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws IM4JavaException
	 */
	@Deprecated
	public static void posWatermarkText(String srcPath,String distPath,String textFile,int fontsize, String color) throws IOException, InterruptedException, IM4JavaException{
		posWatermarkText(srcPath, distPath, textFile, fontsize, 9, 10, color, 100);
	}
	
	/**
	 * ���վŹ���λ���������ˮӡ��Windows Only��
	 * @param srcPath		ԭͼƬ·��
	 * @param distPath		��ͼƬ·��
	 * @param textFile		ˮӡ����UTF-8�ı��ļ�·��
	 * @param fontsize		�����С
	 * @param position		�Ź���λ��[1-9]
	 * @param margin 		�߾�
	 * @param color		��ɫ
	 * @param alpha		͸����
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws IM4JavaException
	 */
	@Deprecated
	public static void posWatermarkText(String srcPath,String distPath,String textFile,int fontsize,int position, int margin, String color,int alpha) throws IOException, InterruptedException, IM4JavaException{
		String textWatermarkPath = getWatermarkFile(textFile,fontsize,color);
    	if("".equals(textWatermarkPath)){
    		return;
    	}
    	posWatermarkImg(srcPath, distPath, textWatermarkPath,position,margin,alpha);
	}
	
	/**
	 * ���������ı�ˮӡ��Windows Only��
	 * @param srcPath		ԭͼƬ·��
	 * @param distPath		��ͼƬ·��
	 * @param textFile		ˮӡ����UTF-8�ı��ļ�·��
	 * @param fontsize		�����С
	 * @param x		ˮӡ��ʼX���
	 * @param y		ˮӡ��ʼY���
	 * @param color		��ɫ
	 * @param alpha		͸����[0-100]
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws IM4JavaException
	 */
	@Deprecated
	public static void watermarkText(String srcPath,String distPath,String textFile,int fontsize,int x, int y, String color,int alpha) throws IOException, InterruptedException, IM4JavaException{
		String textWatermarkPath = getWatermarkFile(textFile,fontsize,color);
    	if("".equals(textWatermarkPath)){
    		return;
    	}
    	watermarkImg(srcPath, distPath, textWatermarkPath, x, y, alpha);
	}
	
	/**
	 * ������Ӣ���ı�ˮӡ��Windows Only��
	 * @param srcPath		ԭͼƬ·��
	 * @param distPath		��ͼƬ·��
	 * @param text		ˮӡӢ������
	 * @param fontsize		�����С
	 * @param x		ˮӡ��ʼX���
	 * @param y		ˮӡ��ʼY���
	 * @param color		��ɫ
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws IM4JavaException
	 */
	@Deprecated
	public synchronized static void watermarkEnText(String srcPath,String distPath, String text, int fontsize,int x, int y, String color) throws IOException, InterruptedException, IM4JavaException{
		IMOperation op = new IMOperation();         
		op.font("Arial");
		op.fill(color);
		op.pointsize(fontsize);
		op.draw("text "+x+","+y+" "+text);  
		op.addImage(srcPath);  
		op.addImage(distPath);  
		System.out.println(op.toString());
		ConvertCmd convert = new ConvertCmd(true);  
		convert.run(op); 
	}
	
	/**
	 * ͼƬ�������ˮӡ
	 * 		��֧��Gif��̬ͼƬ,
	 * 		Ĭ������λ��Ϊ7�����½ǣ�����ɫ����ɫ�����壺���塢���壬�����С��20px���߾ࣺ10px��͸���ȣ�1
	 * @param watermarkText		ˮӡ����
	 * @param targetImg			Ŀ��ͼƬ
	 * @throws IOException
	 */
	public static void pWatermarkText(String watermarkText, String targetImg) throws IOException{
		pWatermarkText(watermarkText,targetImg,7,10,Color.BLACK,20,1f,Font.BOLD,"�??");
	}
	
	/**
	 * ͼƬ�������ˮӡ
	 * 		��֧��Gif��̬ͼƬ,Ĭ��������ɫ����ɫ�����壺���塢���壬�����С��20px���߾ࣺ10px��͸���ȣ�1
	 * @param watermarkText		ˮӡ����
	 * @param targetImg			Ŀ��ͼƬ
	 * @param position			�Ź���λ�ò���,[1-9]
	 * @throws IOException
	 */
	public static void pWatermarkText(String watermarkText, String targetImg,int position) throws IOException{
		pWatermarkText(watermarkText,targetImg,position,10,Color.BLACK,20,1f,Font.BOLD,"�??");
	}
	
	/**
	 * ͼƬ�������ˮӡ
	 * 		��֧��Gif��̬ͼƬ,Ĭ���������壺���塢���壬�����С��20px���߾ࣺ10px��͸���ȣ�1
	 * @param watermarkText		ˮӡ����
	 * @param targetImg			Ŀ��ͼƬ
	 * @param position			�Ź���λ�ò���,[1-9]
	 * @param color		��ɫ
	 * @throws IOException
	 */
	public static void pWatermarkText(String watermarkText, String targetImg,int position,Color color) throws IOException{
		pWatermarkText(watermarkText,targetImg,position,10,color,20,1f,Font.BOLD,"�??");
	}
	
	/**
	 * ͼƬ�������ˮӡ
	 * 		��֧��Gif��̬ͼƬ,Ĭ���������壺���塢���壬�߾ࣺ10px��͸���ȣ�1
	 * @param watermarkText		ˮӡ����
	 * @param targetImg			Ŀ��ͼƬ
	 * @param position			�Ź���λ�ò���,[1-9]
	 * @param color		��ɫ
	 * @param fontSize		�����С
	 * @throws IOException
	 */
	public static void pWatermarkText(String watermarkText, String targetImg,int position,Color color, int fontSize) throws IOException{
		pWatermarkText(watermarkText,targetImg,position,10,color,fontSize,1f,Font.BOLD,"�??");
	}
	
	/**
	 * ͼƬ�������ˮӡ
	 * 		��֧��Gif��̬ͼƬ
	 * @param watermarkText		ˮӡ����
	 * @param targetImg			Ŀ��ͼƬ
	 * @param position			�Ź���λ�ò���,[1-9]
	 * @param color		��ɫ
	 * @param fontSize		�����С
	 * @param alpha		͸���ȣ�ȡֵ��Χ0-1
	 * @throws IOException 
	 */
	public static void pWatermarkText(String watermarkText, String targetImg,int position,Color color, int fontSize, float alpha) throws IOException{
		pWatermarkText(watermarkText,targetImg,position,10,color,fontSize,alpha,Font.BOLD,"�??");
	}

	
	/**
	 * ͼƬ�������ˮӡ(JDK��ʽʵ�֣�û���õ�GraphicsMagick���)
	 * 		��֧��Gif��̬ͼƬ
	 * @param watermarkText		ˮӡ����
	 * @param targetImg		Ŀ��ͼƬ
	 * @param position 	�Ź���λ�ò���,[1-9]
	 * @param margin 		�߾�
	 * @param fontStyle 	��ʽ
	 * @param fontName 	����
	 * @param color		��ɫ
	 * @param fontSize		�����С
	 * @param alpha		͸���ȣ�ȡֵ��Χ0-1
	 * @throws IOException 
	 */
	public synchronized static void pWatermarkText(String watermarkText, String targetImg,int position,int margin, Color color, int fontSize, float alpha, int fontStyle, String fontName) throws IOException{
		if (targetImg.indexOf(".") == -1) {
			return;
		}
		String distImgType = targetImg
				.substring(targetImg.lastIndexOf(".") + 1);
		File tarImgFile = new File(targetImg);
		Image tarImg = ImageIO.read(tarImgFile);
		int width = tarImg.getWidth(null);
		int height = tarImg.getHeight(null);
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g = image.createGraphics();
		if("png".equalsIgnoreCase(distImgType)){
			image = g.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT); 
			g.dispose(); 
			g = image.createGraphics(); 
		}
		int[] xy = getTextXY(position, targetImg, watermarkText, fontSize, margin);
		g.drawImage(tarImg, 0, 0, width, height, null);
		g.setColor(color);
		g.setFont(new Font(fontName, fontStyle, fontSize));
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
				alpha));
		g.drawString(watermarkText, xy[0], xy[1] + fontSize);
		g.dispose();
		ImageIO.write(image, distImgType, new File(targetImg));
	}
	
	/**
	 * ��ȡ�Ƿ�����ı�ˮӡͼƬ�����ִ�й�һ��watermarkTextʱ��UPDATE_TEXT_WATERMARK_IMAGE
	 * ״̬���Զ�������Ϊfalse���ٴ�ִ��watermarkText����ʱ������������ˮӡͼƬ��̣�ֱ�����ˮӡ��
	 * ��������ˮӡ��ִ��Ч��
	 * @return		�Ƿ���Ҫ�����ı�ˮӡͼƬ����״̬
	 */
	public static boolean getUPDATE_TEXT_WATERMARK_IMAGE() {
		return UPDATE_TEXT_WATERMARK_IMAGE;
	}

	/**
	 * ����UPDATE_TEXT_WATERMARK_IMAGE״̬�������Ҫ��������ˮӡͼƬ�ļ�����Ӧ���Ƚ�
	 * UPDATE_TEXT_WATERMARK_IMAGE��������Ϊtrue��������¶����������ִ�С�
	 * @param update_text_watermark_image	�Ƿ��������ˮӡͼƬ
	 */
	public static void setUPDATE_TEXT_WATERMARK_IMAGE(
			boolean update_text_watermark_image) {
		UPDATE_TEXT_WATERMARK_IMAGE = update_text_watermark_image;
	}

	/**
	 * ��ȡ��������ˮӡ����
	 * @return		��ǰ���õ�����ˮӡ����
	 */
	public static String getFont() {
		return _font;
	}

	/**
	 * ������������ˮӡ���壬�����WindowsϵͳĬ�������ǡ����塱�����Ը���������������ã��硰�����źڡ���
	 * Ҳ����������·�����棬�硰C:/WINDOWS/Fonts/MSYH.TTF����
	 * �����Linuxϵͳ�Ļ���Ҫ����Ϊ����·�����硰/usr/share/fonts/simsun.ttc��
	 * @param _font	������ƻ���·��
	 */
	public static void setFont(String font) {
		ImageGraphicsMagickHelper._font = font;
	}

	/**
	 * �ж��Ƿ��Ѿ�����������ˮӡͼƬ�ļ�
	 * @return		��ǰ����ˮӡͼƬ�ļ�����״̬
	 */
	public static boolean IS_UPDATED_TEXT_WATERMARK_IMAGE() {
		return IS_UPDATED_TEXT_WATERMARK_IMAGE;
	}
	
	private static int[] getTextXY(int position, String src, String text, int fontsize, int margin) throws IOException{
		int[] wh = new int[2];
		if(text.getBytes().length==text.length()){
			wh[0] = text.length()*fontsize / 2 +20;
		}else{
			wh[0]  = text.length()*fontsize;
		}
		wh[1]  = fontsize;
		return getXY(getImageSide(src),wh,position,margin,true);
	}
	
	
	private synchronized static void createTextWatermarkImage( int fontsize, String textFile, String color, String wmFile) throws IOException, InterruptedException, IM4JavaException {
		GraphicsMagickCmd  cmd = new GraphicsMagickCmd("convert");
//		ConvertCmd cmd = new ConvertCmd(false);
		IMOperation op = new IMOperation();
		op.background("none");
		op.font(_font==null?getSystemFont():_font);
		op.pointsize(fontsize);
		op.fill(color);
		op.addImage("label:@"+textFile);
		op.addImage(wmFile);
		System.out.println(op.toString());
		cmd.run(op);
		UPDATE_TEXT_WATERMARK_IMAGE = false;
		IS_UPDATED_TEXT_WATERMARK_IMAGE = true;
	}
	
    private static String getDirectory(String text) {
    	String temp = text.replaceAll("\\\\", "/");
		return temp.substring(0,temp.lastIndexOf("/")+1);
	}

	private static String getWatermarkFile(String textFile, int fontsize, String color) throws IOException, InterruptedException, IM4JavaException {
		String watermarkSavePath = getDirectory(textFile);
		String wmFile = watermarkSavePath+"watermark_text.png";
    	File f =new File(wmFile);
    	if(UPDATE_TEXT_WATERMARK_IMAGE){
    		createTextWatermarkImage(fontsize,textFile,color,wmFile);
    	}else if(!f.exists()){
    		createTextWatermarkImage(fontsize,textFile,color,wmFile);
		}
		return f.exists()?wmFile:"";
	}
	
//	��ȡ���壬�����Windows�Ļ�ֱ�ӷ��ء�C:/WINDOWS/Fonts/SIMSUN.TTC��(����)
	private static String getSystemFont() {
		if(isWindows()){
			return "C:/WINDOWS/Fonts/SIMSUN.TTC";
		}else{
			for(String path:fontPathList){
				for(String font:fontList){
					File f = new File(path+font);
					if(f.exists()){
						return path+font;
					}
				}
			}
			throw new RuntimeException("Can't get chinese font. You should put the font[simsun.ttc] in the follow directory: \"/usr/share/fonts/\" \"/usr/share/fonts/zh_CN/\" \"/usr/share/fonts/zh_CN/TrueType/\"");
		}
	}

	private static boolean isWindows() {
		return System.getProperty("os.name").indexOf("Windows")!=-1;
	}
	
	private static int[] getImageSide(String imgPath) throws IOException {
    	int[] side = new int[2];
    	Image img = ImageIO.read(new File(imgPath));
    	side[0] = img.getWidth(null);
    	side[1] =img.getHeight(null);
    	return side;
	}

	private static int[] getXY(int[] image, int[] watermark, int position, int margin, boolean isText) {
    	int[] xy = new int[2];
		if(position==1){
			xy[0] = margin;
			xy[1] = isText?margin+watermark[1]:margin;
		}else if(position==2){
			xy[0] = (image[0]-watermark[0])/2;
			xy[1] = isText?margin+watermark[1]:margin;
		}else if(position==3){
			xy[0] = image[0]-watermark[0]-margin;
			xy[1] = isText?margin+watermark[1]:margin;
		}else if(position==4){
			xy[0] = margin;
			xy[1] = (image[1]-watermark[1])/2;
		}else if(position==5){
			xy[0] = (image[0]-watermark[0])/2;
			xy[1] =  (image[1]-watermark[1])/2;
		}else if(position==6){
			xy[0] = image[0]-watermark[0]-margin;
			xy[1] = (image[1] - watermark[1])/2; 
		}else if(position==7){
			xy[0] =margin;
			xy[1] = image[1] - watermark[1] - margin;
		}else if(position==8){
			xy[0] =  (image[0]-watermark[0])/2;
			xy[1] = image[1] - watermark[1] - margin;
		}else{
			xy[0] = image[0]-watermark[0]-margin;
			xy[1] = image[1] - watermark[1] - margin;
		}
		return xy;
	}


	public static String getMagickPath() {
		return magickPath;
	}

	/**
	 * ����GraphicsMagick��ImageMagick��Windows�µİ�װ·��
	 * 		ע�����������Ҫ��װ�����򽫻ᵼ�²��ֺ����޷���Ч
	 * @param magickPath		GraphicsMagick��ImageMagick��װ·��������������֮���á�;���ָ�
	 */
	public static void setMagickPath(String magickPath) {
		ImageGraphicsMagickHelper.magickPath = magickPath;
		ProcessStarter.setGlobalSearchPath(magickPath);
	}

}
