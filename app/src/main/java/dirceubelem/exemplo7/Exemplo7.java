package dirceubelem.exemplo7;

import android.app.Application;

/**
 * Created by dirceubelem on 10/06/15.
 */
public class Exemplo7 extends Application {

    private static Exemplo7 instance;

    public static Exemplo7 getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

}
