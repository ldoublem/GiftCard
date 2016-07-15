# GiftCard
A beautiful gift Cards for android
#Preview
---
![gif](https://github.com/ldoublem/GiftCard/blob/master/screenshot/shot.gif)
![shot1](https://github.com/ldoublem/GiftCard/blob/master/screenshot/shot1.png)
![shot2](https://github.com/ldoublem/GiftCard/blob/master/screenshot/shot2.png)

#Gradle
compile 'com.ldoublem.GiftCard:giftcardlib:0.1'

---
Usage with xml
```
             <com.ldoblem.giftcardlib.GiftCardView
                android:layout_width="300dp"
                android:layout_height="200dp"
                card:bgStartColor="#11cd6e"
                card:buyButtonColor="#11cd6e"
                card:cardBgColor="#33475f"
                card:buttonByText="购买"
                card:cardGiftTitle="礼物卡"
                card:cardGiftLogo="@mipmap/ic_launcher"
                card:buttonCheckText="确定"
                card:checkButtonColor="#2c2c2c"
                card:bgPackColor="#56abe4"
                card:priceTextColor="#fff"/>
```
Usage with java
```
        mGiftCardView = (GiftCardView) findViewById(R.id.gc_shop);
        mGiftCardView.setMTitle("苹果礼券");
        mGiftCardView.setMPrice(188);
        mGiftCardView.setButtonBuyText("买");
        mGiftCardView.setButtonCheckText("确定");
        mGiftCardView.setCardTip("请检查你的购物单");
        mGiftCardView.setCardBgColor(Color.BLACK);
        mGiftCardView.setGiftLogo(R.mipmap.ic_launcher);
        mGiftCardView.setBgStartColor(Color.BLACK);
        mGiftCardView.setBgEndColor(Color.BLACK);
        mGiftCardView.setBuyButtonColor(Color.BLACK);
        mGiftCardView.setCheckButtonColor(Color.BLACK);
        mGiftCardView.setPriceTextColor(Color.BLACK);
        mGiftCardView.setBgPackBgColor(Color.BLACK);
        mGiftCardView.setOnCheckOut(new GiftCardView.Buyer("陆先生", "中国浙江省",
                        "杭州市,西湖区,南山路100号", "有效期:3天"),
                new GiftCardView.OnCheckOut() {
                    @Override
                    public void Ok(int vid) {
                        
                    }
                });
```

## About me

An android developer in Hangzhou.

If you want to make friends with me, You can email to me.
my [email](mailto:1227102260@qq.com) :smiley:


License
=======

    The MIT License (MIT)

	Copyright (c) 2016 ldoublem

	Permission is hereby granted, free of charge, to any person obtaining a copy
	of this software and associated documentation files (the "Software"), to deal
	in the Software without restriction, including without limitation the rights
	to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
	copies of the Software, and to permit persons to whom the Software is
	furnished to do so, subject to the following conditions:

	The above copyright notice and this permission notice shall be included in all
	copies or substantial portions of the Software.

	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
	IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
	FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
	AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
	LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
	OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
	SOFTWARE.







