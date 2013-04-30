import gotoandplay.utils.defrag.DFResLoader;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;

public class Test {

        public static void main(String[] args) throws Exception{
                DFResLoader loader = new DFResLoader(Test.class.getClassLoader());
                loader.init(new FileInputStream("C:/Program Files (x86)/SmartFoxServer2X 2.0.1/SFS2X/lib/Lib/sfs2x-lms.jar"));
                
                ISFSObject license = new SFSObject();
                license.putUtfString("customer", "");//授权人，为空则为共享版，否则个人版
                license.putUtfString("bind", "127.0.0.1");//限制IP
                license.putInt("users", -1);//连接数上限，-1为无限数量
                license.putLong("expire", 0);//expireTime 版权到期时间，0为无限制
                license.putBool("private", false);//私有？
                
                Class BuilderClazz = loader.loadClass("sfs.lms.o");
                Method m =  BuilderClazz.getMethod("F", ISFSObject.class);
                m.setAccessible(true);
                
                Constructor c = BuilderClazz.getDeclaredConstructors()[0];
                c.setAccessible(true);
                Object licenseBuilder = c.newInstance(new Object[1]);
                
                Object licenseData = m.invoke(licenseBuilder, license);
                new FileOutputStream("c:/license.2x").write((byte[])licenseData);
        }
}