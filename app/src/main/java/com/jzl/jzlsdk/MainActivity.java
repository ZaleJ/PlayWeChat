package com.jzl.jzlsdk;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXTextObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        api = WXAPIFactory.createWXAPI(this, APP_ID); // 创建APP
        api.registerApp(APP_ID);                    // 注册APP

        btnShare = (Button) findViewById(R.id.btnShare);
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SendMessageToWX.Req req = new SendMessageToWX.Req();     //  发送消息到微信

                req.transaction = "text"+System.currentTimeMillis();     // 保证唯一的标识？？
                req.scene = SendMessageToWX.Req.WXSceneTimeline;
                req.message = new WXMediaMessage();
                req.message.description = "Hello";
                req.message.mediaObject = new WXTextObject("Hello WX");

                api.sendReq(req);

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onDestroy() {

        api.unregisterApp();

        super.onDestroy();
    }

    private IWXAPI api;
    public static final String APP_ID = "wx12bb27c21b55be24";
    private Button btnShare;
    // private WXTextObject textObject;

}
