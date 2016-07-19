package com.ldoublem.giftcard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import com.ldoblem.giftcardlib.GiftCardView;
import com.ldoblem.giftcardlib.models.Buyer;

public class MainActivity extends AppCompatActivity implements GiftCardView.OnCheckOut {
    GiftCardView mGiftCardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //去掉信息栏
        setContentView(R.layout.activity_main);
        mGiftCardView = (GiftCardView) findViewById(R.id.gc_shop);
//        mGiftCardView.setMTitle("苹果礼券");
//        mGiftCardView.setMPrice(188);
//        mGiftCardView.setButtonBuyText("买");
//        mGiftCardView.setButtonCheckText("确定");
//        mGiftCardView.setCardTip("请检查你的购物单");
//        mGiftCardView.setCardBgColor(Color.BLACK);
//        mGiftCardView.setGiftLogo(R.mipmap.ic_launcher);
//        mGiftCardView.setBgStartColor(Color.BLACK);
//        mGiftCardView.setBgEndColor(Color.BLACK);
//        mGiftCardView.setBuyButtonColor(Color.BLACK);
//        mGiftCardView.setCheckButtonColor(Color.BLACK);
//        mGiftCardView.setPriceTextColor(Color.BLACK);
//        mGiftCardView.setBgPackBgColor(Color.BLACK);
        mGiftCardView.setOnCheckOut(new Buyer("陆先生", "中国浙江省",
                        "杭州市,西湖区,南山路100号", "有效期:3天"),
                this);

    }

    @Override
    public void ok(int vid) {
        if (vid == R.id.gc_shop) {
            Toast.makeText(MainActivity.this, "Thank you", Toast.LENGTH_SHORT).show();
            mGiftCardView.restore();
        }
    }
}
