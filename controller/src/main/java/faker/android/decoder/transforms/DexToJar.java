package faker.android.decoder.transforms;

import faker.android.decoder.api.AndroidProject;
import faker.android.decoder.api.Apk;
import faker.android.decoder.dex2jar.Dex2jar;
import faker.android.decoder.pipeline.*;

import java.io.File;

public class DexToJar extends Transform  {

    public DexToJar(Apk apk, AndroidProject androidProject) {
        super(apk, androidProject);
    }

    @Override
    public boolean transform(TransformInvocation transformInvocation) {
        transformInvocation.callBack("Translating dexes to java scaffodding jar....");
        try {
            File javaScaffoding = androidProject.getJavaScaffoding();
            if(javaScaffoding.exists()){
                javaScaffoding.delete();
            }
            javaScaffoding.mkdirs();
            Dex2jar.toJar(apk.getApkFile(),javaScaffoding);
        }catch (Exception e){
            transformInvocation.callBack("Translating dexes to java scaffodding jar happen exception....");
        }
        return true;
    }
}
