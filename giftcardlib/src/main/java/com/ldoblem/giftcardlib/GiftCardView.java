package com.ldoblem.giftcardlib;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Toast;

/**
 * Created by lumingmin on 16/7/14.
 */

public class GiftCardView extends View {
    Paint mPaintBg;
    Paint mPaintText;
    Paint mPaintBuyButton;

    Path mPathBg;
    RectF rectFBg;
    RectF rectFBgMove;

    Path mPathPackLeft;
    Path mPathPackTop;
    Path mPathPackBottom;

    Path mPathPackRight;


    Path mPathsilkTop;
    Path mPathsilkBottom;
    Path mPathsilkLeft;
    Path mPathsilkRight;


    RectF rectFBuyButton;
    RectF rectFCheckButton;


    float mPadding = 0f;

    float mCircular = 0f;
    Shader mShader;

    String mTitle = "Gift Card";
    float mPrice = 25.00f;

    String mButtonBuyText = "Buy";
    String mButtonCheckText = "ok";
    String cardTip = "YOU ORDER WILL BE SHIPPED TO:";

    float mBuyButtonH = 0f;
    float mBuyButtonW = 0f;

    int bgStartColor = Color.rgb(231, 0, 148);
    int bgEndColor = Color.rgb(164, 13, 158);
    int buyButtonColor = Color.rgb(243, 152, 0);


    int checkButtonColor = Color.rgb(8, 156, 239);

    int cardBgColor = Color.WHITE;
    int giftLogo = R.drawable.apple;
    int bgPackBgColor = Color.rgb(252, 44, 44);


    int mPriceTextColor = Color.rgb(150, 150, 150);

    Buyer mBuyer;

    OnCheckOut mOnCheckOut;


    boolean pressBuyButton = false;
    boolean pressCheckButton = false;


    public void setMTitle(String text) {
        this.mTitle = text;
    }

    public void setMPrice(float price) {
        this.mPrice = price;
    }

    public void setCardTip(String text) {
        this.cardTip = text;
    }

    public void setButtonBuyText(String text) {
        this.mButtonBuyText = text;
    }

    public void setButtonCheckText(String text) {
        this.mButtonCheckText = text;
    }

    public void setCardBgColor(int color) {
        this.cardBgColor = color;
    }

    public void setGiftLogo(int id) {
        this.giftLogo = id;
    }

    public void setBgStartColor(int bgStartColor) {
        this.bgStartColor = bgStartColor;
    }

    public void setBgEndColor(int bgEndColor) {
        this.bgEndColor = bgEndColor;
    }

    public void setBuyButtonColor(int buyButtonColor) {
        this.buyButtonColor = buyButtonColor;
    }

    public void setCheckButtonColor(int checkButtonColor) {
        this.checkButtonColor = checkButtonColor;
    }

    public void setBgPackBgColor(int bgPackBgColor) {
        this.bgPackBgColor = bgPackBgColor;
    }

    public void setPriceTextColor(int mPriceTextColor) {
        this.mPriceTextColor = mPriceTextColor;
    }


    public void setOnCheckOut(Buyer buyer, OnCheckOut onCheckOut) {
        this.mBuyer = buyer;
        this.mOnCheckOut = onCheckOut;
        invalidate();
    }


    public GiftCardView(Context context) {
        this(context, null);
    }

    public GiftCardView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GiftCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CardView);
        if (typedArray != null) {
//            isCanvasLine = typedArray.getBoolean(R.styleable.SelectView_isCanvasLine, true);
//            mTextSize = typedArray.getDimension(R.styleable.SelectView_textSize, dip2px(12));
            bgStartColor = typedArray.getColor(R.styleable.CardView_bgStartColor, bgStartColor);
            bgEndColor = typedArray.getColor(R.styleable.CardView_bgEndColor, bgEndColor);
            buyButtonColor = typedArray.getColor(R.styleable.CardView_buyButtonColor, buyButtonColor);
            checkButtonColor = typedArray.getColor(R.styleable.CardView_checkButtonColor, checkButtonColor);
            cardBgColor = typedArray.getColor(R.styleable.CardView_cardBgColor, cardBgColor);
            bgPackBgColor = typedArray.getColor(R.styleable.CardView_bgPackColor, bgPackBgColor);

            mPriceTextColor = typedArray.getColor(R.styleable.CardView_priceTextColor, mPriceTextColor);


            giftLogo = typedArray.getResourceId(R.styleable.CardView_cardGiftLogo, R.drawable.apple);
            mTitle = typedArray.getString(R.styleable.CardView_cardGiftTitle);
            if (mTitle == null)
                mTitle = "Gift Card";

            mButtonCheckText = typedArray.getString(R.styleable.CardView_buttonCheckText);
            if (mButtonCheckText == null)
                mButtonCheckText = "ok";
            mButtonBuyText = typedArray.getString(R.styleable.CardView_buttonByText);
            if (mButtonBuyText == null)
                mButtonBuyText = "Buy";


            mPrice = typedArray.getFloat(R.styleable.CardView_cardGiftPrice, 0);

//            mUnit = typedArray.getDimension(R.styleable.SelectView_unitSize, 50.f);
//            mUnitLongLine = typedArray.getInteger(R.styleable.SelectView_unitLongLine, 5);
//            textSelectColor = typedArray.getColor(R.styleable.SelectView_textSelectColor, Color.rgb(151, 151, 151));

            typedArray.recycle();
        }
        initPaint();
    }


    public void setBuyer(Buyer buyer) {
        this.mBuyer = buyer;
    }

    private void initPaint() {

        mBuyer = new Buyer("ldoublem", "Zhejiang Province",
                "Hangzhou , Xihu , Nanshan Road", "Available to ship: 1 business day");

        mPaintBg = new Paint();
        mPaintBg.setAntiAlias(true);
        mPaintBg.setColor(Color.WHITE);
        mPaintBg.setStyle(Paint.Style.FILL);

        mPaintText = new Paint();
        mPaintText.setAntiAlias(true);
        mPaintText.setColor(Color.WHITE);
        mPaintText.setStyle(Paint.Style.FILL);


        mPaintBuyButton = new Paint();
        mPaintBuyButton.setAntiAlias(true);
        mPaintBuyButton.setColor(buyButtonColor);
        mPaintBuyButton.setStyle(Paint.Style.FILL);


        mPathBg = new Path();
        mPathBg.reset();
        mPathPackLeft = new Path();
        mPathPackBottom = new Path();
        mPathPackTop = new Path();
        mPathPackRight = new Path();
        mPathPackLeft.reset();
        mPathPackRight.reset();
        mPathPackTop.reset();
        mPathPackBottom.reset();


        mPathsilkTop = new Path();
        mPathsilkBottom = new Path();
        mPathsilkRight = new Path();
        mPathsilkLeft = new Path();
        mPathsilkTop.reset();
        mPathsilkBottom.reset();
        mPathsilkLeft.reset();
        mPathsilkRight.reset();
        rectFBg = new RectF();
        rectFBgMove = new RectF();
        rectFBuyButton = new RectF();
        rectFCheckButton = new RectF();
        mPadding = dip2px(1);
        mShader = new Shader();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthSpecMode == MeasureSpec.AT_MOST
                && heightSpecMode == MeasureSpec.AT_MOST) {
            // 指定wrap_content模式（MeasureSpec.AT_MOST）的默认宽高，比如200px
            setMeasuredDimension(dip2px(200), dip2px(150));
        } else if (widthSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(dip2px(200), heightSpecSize);
        } else if (heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSpecSize, dip2px(150));
        }
    }


    private void drawBg(Canvas canvas) {
        mPathBg.reset();
        mPathBg.moveTo(rectFBg.left, rectFBg.top + rectFBg.height() / 3f * 2f);
        mPathBg.lineTo(rectFBg.left, rectFBg.top + mCircular);
        mPathBg.quadTo(rectFBg.left, rectFBg.top,
                rectFBg.left + mCircular, rectFBg.top);
        mPathBg.lineTo(rectFBg.right - mCircular, rectFBg.top);
        mPathBg.quadTo(rectFBg.right, rectFBg.top,
                rectFBg.right, rectFBg.top + mCircular);
        mPathBg.lineTo(rectFBg.right, rectFBg.top + rectFBg.height() / 3f * 2f);
        mPathBg.close();

        mPaintBg.setColor(cardBgColor);
        canvas.drawPath(mPathBg, mPaintBg);


        mPaintText.setTextSize(mCircular - 1);

        mBuyButtonW = (getFontHeight(mPaintText, mButtonBuyText)) * 3f * 1.5f;

        mBuyButtonH = mBuyButtonW / 3f;

        rectFBgMove.top = rectFBg.top;
        rectFBgMove.bottom = rectFBg.bottom;
        rectFBgMove.left = rectFBg.left - mAnimatedBgValue * (rectFBg.width() - mCircular * 2 - mBuyButtonW);
        rectFBgMove.right = rectFBg.right - mAnimatedBgValue * (rectFBg.width() - mCircular * 2 - mBuyButtonW);


    }


    private void drawCardTopBg(Canvas canvas) {
        mPathBg.reset();

        float mRightLeft = mCircular * mAnimatedBgValue;
        float mTop = mAnimatedBgValue * rectFBg.height() / 3f * 2f;

        if (rectFBg.height() / 3f * 2f - mTop < mCircular / 2) {
            if (mAnimatedBgValue <= 1.0f) {
                mPathBg.moveTo(rectFBg.left + mRightLeft, rectFBg.top + rectFBg.height() / 3f * 2f);
                mPathBg.lineTo(rectFBg.left + mRightLeft, rectFBg.top + rectFBg.height() / 3f * 2f - 2);
                mPathBg.lineTo(rectFBg.right - mRightLeft, rectFBg.top + rectFBg.height() / 3f * 2f - 2);
                mPathBg.lineTo(rectFBg.right - mRightLeft, rectFBg.top + rectFBg.height() / 3f * 2f);
            } else {
                mRightLeft = mCircular;
                mTop = rectFBg.height() / 3f * 2f;
                mPathBg.moveTo(rectFBg.left + mRightLeft, rectFBg.top + rectFBg.height() / 3f * 2f);
                mPathBg.lineTo(rectFBg.left + mRightLeft, rectFBg.top + rectFBg.height() / 3f * 2f - 2);
                mPathBg.lineTo(rectFBg.right - mRightLeft, rectFBg.top + rectFBg.height() / 3f * 2f - 2);
                mPathBg.lineTo(rectFBg.right - mRightLeft, rectFBg.top + rectFBg.height() / 3f * 2f);

            }

        } else {
            mPathBg.moveTo(rectFBg.left + mRightLeft, rectFBg.top + rectFBg.height() / 3f * 2f);
            mPathBg.lineTo(rectFBg.left + mRightLeft, rectFBg.top + mCircular + mTop);
            mPathBg.quadTo(rectFBg.left + mRightLeft, rectFBg.top + mTop,
                    rectFBg.left + mCircular + mRightLeft, rectFBg.top + mTop);
            mPathBg.lineTo(rectFBg.right - mCircular - mRightLeft, rectFBg.top + mTop);
            mPathBg.quadTo(rectFBg.right - mRightLeft, rectFBg.top + mTop,
                    rectFBg.right - mRightLeft, rectFBg.top + mCircular + mTop);
            mPathBg.lineTo(rectFBg.right - mRightLeft, rectFBg.top + rectFBg.height() / 3f * 2f);
            mPathBg.close();

        }
        setShader(mPaintBg, bgStartColor,
                bgEndColor);
        canvas.drawPath(mPathBg, mPaintBg);
        mPaintBg.setShader(null);
    }

    private void drawCardTopBgShadow(Canvas canvas) {
        if (mAnimatedBgValue > 0.1f)
            return;
        mPathBg.reset();

        mPathBg.moveTo(rectFBg.left, rectFBg.top + rectFBg.height() / 3f * 2f);
        mPathBg.lineTo(rectFBg.left, rectFBg.top + rectFBg.height() / 3f * 2f -
                (rectFBg.height() / 3f * 2f) / 4f);
        mPathBg.quadTo(rectFBg.left, rectFBg.top + rectFBg.height() / 3f * 2f,
                rectFBg.right
                , rectFBg.top + rectFBg.height() / 3f * 2f / 2f
        );
        mPathBg.lineTo(rectFBg.right, rectFBg.top + rectFBg.height() / 3f * 2f);
        mPathBg.close();

        mPaintBg.setColor(Color.argb(30, 255, 255, 255));
        canvas.drawPath(mPathBg, mPaintBg);
        mPaintBg.setShader(null);
    }

    private void drawLogo(Canvas canvas) {
        if (mAnimatedBgValue >= 1f)
            return;
        mPaintBg.setColor(Color.WHITE);
        Bitmap ios = setBitmapSize(giftLogo, (int) ((int) (rectFBg.height() / 4f * 3f / 3f) * (1)));
        canvas.drawBitmap(ios, rectFBg.centerX() - ios.getWidth() / 2,
                rectFBg.top + rectFBg.height() / 3f * 2f / 2f - ios.getHeight() / 2 + rectFBg.height() / 3f * 1.5f * (mAnimatedBgValue), mPaintBg);
    }


    private void drawCardBottomBg(Canvas canvas) {
        mPathBg.reset();
        mPathBg.moveTo(rectFBg.left, rectFBg.top + rectFBg.height() / 3f * 2f);
        mPathBg.lineTo(rectFBg.left, rectFBg.bottom - mCircular);
        mPathBg.quadTo(rectFBg.left, rectFBg.bottom,
                rectFBg.left + mCircular, rectFBg.bottom);
        mPathBg.lineTo(rectFBg.right - mCircular, rectFBg.bottom);
        mPathBg.quadTo(rectFBg.right, rectFBg.bottom,
                rectFBg.right, rectFBg.bottom - mCircular);
        mPathBg.lineTo(rectFBg.right, rectFBg.top + rectFBg.height() / 3f * 2f);
        mPathBg.close();

        mPaintBg.setColor(cardBgColor);
        canvas.drawPath(mPathBg, mPaintBg);


    }


    private void drawTitleAndPrice(Canvas canvas) {
        setShader(mPaintText, bgStartColor,
                bgEndColor);
        mPaintText.setTextSize(mCircular + 1);
        canvas.drawText(mTitle, rectFBgMove.left + mCircular,
                rectFBgMove.top + rectFBgMove.height() / 3f * 2f + rectFBgMove.height() / 3f / 2f -
                        getFontHeight(mPaintText, mTitle) / 2f
                ,
                mPaintText);
        mPaintText.setShader(null);

        mPaintText.setColor(mPriceTextColor);
        mPaintText.setTextSize(mCircular - 1);


        String price = "$" + new java.text.DecimalFormat("#.00").format(mPrice);

        canvas.drawText(price, rectFBgMove.left + mCircular,
                rectFBgMove.top + rectFBgMove.height() / 3f * 2f + rectFBgMove.height() / 3f / 2f +
                        getFontHeight(mPaintText, price)
                ,
                mPaintText);
    }

    private void drawBuyButton(Canvas canvas) {
        mPaintBuyButton.setColor(buyButtonColor);
        mPaintText.setTextSize(mCircular - 1);
        String price = "$" + new java.text.DecimalFormat("#.00").format(mPrice);
        rectFBuyButton.top = rectFBgMove.top + rectFBgMove.height() / 3f * 2f + rectFBgMove.height() / 3f / 2f -
                mBuyButtonH
        ;
        rectFBuyButton.bottom = rectFBgMove.top + rectFBgMove.height() / 3f * 2f + rectFBgMove.height() / 3f / 2f +
                mBuyButtonH;
        rectFBuyButton.right = rectFBgMove.right - mCircular;
        rectFBuyButton.left = rectFBgMove.right - mCircular - mBuyButtonW;


        if (pressBuyButton) {
            rectFBuyButton.top = rectFBuyButton.top + 4;
            rectFBuyButton.left = rectFBuyButton.left + 4;
            rectFBuyButton.bottom = rectFBuyButton.bottom - 4;
            rectFBuyButton.right = rectFBuyButton.right - 4;

        }


        canvas.drawRoundRect(rectFBuyButton, mCircular / 2f, mCircular / 2f, mPaintBuyButton);
        mPaintText.setColor(Color.WHITE);
        canvas.drawText(mButtonBuyText, rectFBuyButton.centerX() - getFontlength(mPaintText, mButtonBuyText) / 2f
                , rectFBuyButton.centerY() + getFontHeight(mPaintText, mButtonBuyText) / 3f,
                mPaintText);

        if (mAmAnimatedPackValue > 0f && mAmAnimatedPackValue <= 0.4f) {


            float packValueFirst = mAmAnimatedPackValue / 0.4f;


            float maxHigh = rectFBuyButton.height() / 2f * 0.8f;

            mPaintBuyButton.setColor(bgPackBgColor);
            mPathPackTop.reset();
            mPathPackTop.moveTo(rectFBuyButton.left + mCircular / 2f, rectFBuyButton.top);
            mPathPackTop.lineTo(rectFBuyButton.right - mCircular / 2f, rectFBuyButton.top);

            mPathPackTop.lineTo(rectFBuyButton.right - mCircular / 2f -
                    maxHigh * (1 - packValueFirst), rectFBuyButton.top -
                    maxHigh * (1 - packValueFirst));

            mPathPackTop.lineTo(rectFBuyButton.left + mCircular / 2f +
                            maxHigh * (1 - packValueFirst)
                    , rectFBuyButton.top -
                            maxHigh * (1 - packValueFirst));

            mPathPackTop.close();
            mPaintBuyButton.setAlpha(200);
            canvas.drawPath(mPathPackTop, mPaintBuyButton);

            mPathPackTop.reset();
            mPathPackTop.moveTo(rectFBuyButton.right - mCircular / 2f -
                    maxHigh * (1 - packValueFirst), rectFBuyButton.top -
                    maxHigh * (1 - packValueFirst));

            mPathPackTop.lineTo(rectFBuyButton.left + mCircular / 2f +
                            maxHigh * (1 - packValueFirst)
                    , rectFBuyButton.top -
                            maxHigh * (1 - packValueFirst));

            mPathPackTop.lineTo(
                    rectFBuyButton.centerX(),
                    rectFBuyButton.top -
                            maxHigh * (1 - packValueFirst) +
                            maxHigh * packValueFirst

            );
            mPathPackTop.close();
            mPaintBuyButton.setAlpha(255);

            canvas.drawPath(mPathPackTop, mPaintBuyButton);



            mPathPackBottom.reset();
            mPathPackBottom.moveTo(rectFBuyButton.left + mCircular / 2f, rectFBuyButton.bottom);
            mPathPackBottom.lineTo(rectFBuyButton.right - mCircular / 2f, rectFBuyButton.bottom);


            mPathPackBottom.lineTo(rectFBuyButton.right - mCircular / 2f -
                    maxHigh * (1 - packValueFirst), rectFBuyButton.bottom +
                    maxHigh * (1 - packValueFirst));

            mPathPackBottom.lineTo(rectFBuyButton.left + mCircular / 2f +
                            maxHigh * (1 - packValueFirst)
                    , rectFBuyButton.bottom +
                            maxHigh * (1 - packValueFirst));

            mPathPackBottom.close();
            mPaintBuyButton.setAlpha(200);
            canvas.drawPath(mPathPackBottom, mPaintBuyButton);


            mPathPackBottom.reset();
            mPathPackBottom.moveTo(rectFBuyButton.right - mCircular / 2f -
                    maxHigh * (1 - packValueFirst), rectFBuyButton.bottom +
                    maxHigh * (1 - packValueFirst));

            mPathPackBottom.lineTo(rectFBuyButton.left + mCircular / 2f +
                            maxHigh * (1 - packValueFirst)
                    , rectFBuyButton.bottom +
                            maxHigh * (1 - packValueFirst));
            mPathPackBottom.lineTo(
                    rectFBuyButton.centerX(),
                    rectFBuyButton.bottom +
                            maxHigh * (1 - packValueFirst) -
                            maxHigh * packValueFirst

            );
            mPathPackBottom.close();
            mPaintBuyButton.setAlpha(255);

            canvas.drawPath(mPathPackBottom, mPaintBuyButton);




            float maxWidth = rectFBuyButton.height() / 2f * 0.5f;

            mPathPackLeft.reset();
            mPathPackLeft.moveTo(rectFBuyButton.left, rectFBuyButton.top + mCircular / 2f);
            mPathPackLeft.lineTo(rectFBuyButton.left, rectFBuyButton.bottom - mCircular / 2f);
            mPathPackLeft.lineTo(rectFBuyButton.left -
                            maxWidth * (1 - packValueFirst)

                    , rectFBuyButton.bottom - mCircular / 2f -
                            maxWidth * (1 - packValueFirst)
            );
            mPathPackLeft.lineTo(rectFBuyButton.left - maxWidth * (1 - packValueFirst), rectFBuyButton.top + mCircular / 2f
                    + maxWidth * (1 - packValueFirst));

            mPathPackLeft.close();
            mPaintBuyButton.setAlpha(200);
            canvas.drawPath(mPathPackLeft, mPaintBuyButton);


            mPathPackLeft.reset();
            mPathPackLeft.moveTo(rectFBuyButton.left -
                            maxWidth * (1 - packValueFirst)

                    , rectFBuyButton.bottom - mCircular / 2f -
                            maxWidth * (1 - packValueFirst)
            );
            mPathPackLeft.lineTo(rectFBuyButton.left - maxWidth * (1 - packValueFirst), rectFBuyButton.top + mCircular / 2f
                    + maxWidth * (1 - packValueFirst));

            mPathPackLeft.lineTo(
                    rectFBuyButton.left - maxWidth * (1 - packValueFirst) +
                            maxWidth * packValueFirst


                    , rectFBuyButton.centerY()


            );

            mPathPackLeft.close();
            mPaintBuyButton.setAlpha(255);
            canvas.drawPath(mPathPackLeft, mPaintBuyButton);




            mPathPackRight.reset();
            mPathPackRight.moveTo(rectFBuyButton.right, rectFBuyButton.top + mCircular / 2f);
            mPathPackRight.lineTo(rectFBuyButton.right, rectFBuyButton.bottom - mCircular / 2f);
            mPathPackRight.lineTo(rectFBuyButton.right +
                            maxWidth * (1 - packValueFirst)

                    , rectFBuyButton.bottom - mCircular / 2f -
                            maxWidth * (1 - packValueFirst)
            );
            mPathPackRight.lineTo(rectFBuyButton.right + maxWidth * (1 - packValueFirst), rectFBuyButton.top + mCircular / 2f
                    + maxWidth * (1 - packValueFirst));

            mPathPackRight.close();
            mPaintBuyButton.setAlpha(200);
            canvas.drawPath(mPathPackRight, mPaintBuyButton);


            mPathPackRight.reset();
            mPathPackRight.moveTo(rectFBuyButton.right +
                            maxWidth * (1 - packValueFirst)

                    , rectFBuyButton.bottom - mCircular / 2f -
                            maxWidth * (1 - packValueFirst)
            );
            mPathPackRight.lineTo(rectFBuyButton.right + maxWidth * (1 - packValueFirst), rectFBuyButton.top + mCircular / 2f
                    + maxWidth * (1 - packValueFirst));

            mPathPackRight.lineTo(
                    rectFBuyButton.right + maxWidth * (1 - packValueFirst) -
                            maxWidth * packValueFirst


                    , rectFBuyButton.centerY()


            );

            mPathPackRight.close();
            mPaintBuyButton.setAlpha(255);
            canvas.drawPath(mPathPackRight, mPaintBuyButton);


        }

        else if(mAmAnimatedPackValue > 0.4f && mAmAnimatedPackValue <= 0.5f)
        {
            mPaintBuyButton.setColor(bgPackBgColor);

            canvas.drawRoundRect(rectFBuyButton, mCircular / 2f, mCircular / 2f, mPaintBuyButton);

            float packValueFirst = (mAmAnimatedPackValue - 0.4f) / 0.1f;

            mPaintBuyButton.setColor(Color.rgb(253, 209, 48));
//            mPaintBuyButton.setAlpha(200);
            mPathsilkTop.reset();
            mPathsilkTop.moveTo(rectFBuyButton.left + mCircular * 1.1f, rectFBuyButton.top);
            mPathsilkTop.lineTo(rectFBuyButton.left + mCircular * 1.1f + mCircular / 1.5f, rectFBuyButton.top);
            mPathsilkTop.lineTo(rectFBuyButton.left + mCircular * 1.1f + mCircular / 1.5f, rectFBuyButton.top
                    -   packValueFirst *mCircular / 2f
            );
            mPathsilkTop.lineTo(rectFBuyButton.left + mCircular * 1.1f, rectFBuyButton.top

                    -   packValueFirst * mCircular / 2f
            );

            mPathsilkTop.close();
            canvas.drawPath(mPathsilkTop, mPaintBuyButton);





            mPathsilkBottom.reset();
            mPathsilkBottom.moveTo(rectFBuyButton.left + mCircular * 1.1f, rectFBuyButton.bottom);
            mPathsilkBottom.lineTo(rectFBuyButton.left + mCircular * 1.1f + mCircular / 1.5f, rectFBuyButton.bottom);
            mPathsilkBottom.lineTo(rectFBuyButton.left + mCircular * 1.1f + mCircular / 1.5f, rectFBuyButton.bottom

                     + mCircular / 2f*packValueFirst
            );
            mPathsilkBottom.lineTo(rectFBuyButton.left + mCircular * 1.1f, rectFBuyButton.bottom

                   + mCircular / 2f*packValueFirst
            );

            mPathsilkBottom.close();
            canvas.drawPath(mPathsilkBottom, mPaintBuyButton);






            mPathsilkLeft.reset();
            mPathsilkLeft.moveTo(rectFBuyButton.left, rectFBuyButton.top + mCircular / 1.4f);
            mPathsilkLeft.lineTo(rectFBuyButton.left, rectFBuyButton.top + mCircular / 1.4f + mCircular / 1.5f);
            mPathsilkLeft.lineTo(rectFBuyButton.left -mCircular/2f*packValueFirst
                    , rectFBuyButton.top + mCircular / 1.4f + mCircular / 1.5f
            );
            mPathsilkLeft.lineTo(rectFBuyButton.left -mCircular/2f*packValueFirst
                    , rectFBuyButton.top + mCircular / 1.4f
            );

            mPathsilkLeft.close();
            canvas.drawPath(mPathsilkLeft, mPaintBuyButton);






            mPathsilkRight.reset();
            mPathsilkRight.moveTo(rectFBuyButton.right, rectFBuyButton.top + mCircular / 1.4f);
            mPathsilkRight.lineTo(rectFBuyButton.right, rectFBuyButton.top + mCircular / 1.4f + mCircular / 1.5f);
            mPathsilkRight.lineTo(rectFBuyButton.right + mCircular/2f*packValueFirst
                    , rectFBuyButton.top + mCircular / 1.4f + mCircular / 1.5f
            );
            mPathsilkRight.lineTo(rectFBuyButton.right + mCircular/2f*packValueFirst
                    , rectFBuyButton.top + mCircular / 1.4f
            );

            mPathsilkRight.close();
            canvas.drawPath(mPathsilkRight, mPaintBuyButton);





        }

        else if (mAmAnimatedPackValue > 0.5f && mAmAnimatedPackValue <= 1f) {


            mPaintBuyButton.setColor(bgPackBgColor);

            canvas.drawRoundRect(rectFBuyButton, mCircular / 2f, mCircular / 2f, mPaintBuyButton);

            float packValueSecond = (mAmAnimatedPackValue - 0.5f) / 0.5f;
            if (packValueSecond >= 1f)
                packValueSecond = 1f;
            mPaintBuyButton.setColor(Color.rgb(253, 209, 48));
            float maxSilkTopH = rectFBuyButton.height() / 3f + mCircular;
            float maxSilkBottpmH = rectFBuyButton.height() / 3f * 2 + mCircular;
            mPathsilkTop.reset();
            mPathsilkTop.moveTo(rectFBuyButton.left + mCircular * 1.1f, rectFBuyButton.top);
            mPathsilkTop.lineTo(rectFBuyButton.left + mCircular * 1.1f + mCircular / 1.5f, rectFBuyButton.top);
            mPathsilkTop.lineTo(rectFBuyButton.left + mCircular * 1.1f + mCircular / 1.5f, rectFBuyButton.top

                    + maxSilkTopH * packValueSecond - mCircular / 2f
            );
            mPathsilkTop.lineTo(rectFBuyButton.left + mCircular * 1.1f, rectFBuyButton.top

                    + maxSilkTopH * packValueSecond - mCircular / 2f
            );

            mPathsilkTop.close();
            canvas.drawPath(mPathsilkTop, mPaintBuyButton);


            mPathsilkBottom.reset();
            mPathsilkBottom.moveTo(rectFBuyButton.left + mCircular * 1.1f, rectFBuyButton.bottom);
            mPathsilkBottom.lineTo(rectFBuyButton.left + mCircular * 1.1f + mCircular / 1.5f, rectFBuyButton.bottom);
            mPathsilkBottom.lineTo(rectFBuyButton.left + mCircular * 1.1f + mCircular / 1.5f, rectFBuyButton.bottom

                    - maxSilkBottpmH * packValueSecond + mCircular / 2f
            );
            mPathsilkBottom.lineTo(rectFBuyButton.left + mCircular * 1.1f, rectFBuyButton.bottom

                    - maxSilkBottpmH * packValueSecond + mCircular / 2f
            );

            mPathsilkBottom.close();
            canvas.drawPath(mPathsilkBottom, mPaintBuyButton);


            float maxSilkLeftW = mCircular + mCircular;


            mPathsilkLeft.reset();
            mPathsilkLeft.moveTo(rectFBuyButton.left, rectFBuyButton.top + mCircular / 1.4f);
            mPathsilkLeft.lineTo(rectFBuyButton.left, rectFBuyButton.top + mCircular / 1.4f + mCircular / 1.5f);
            mPathsilkLeft.lineTo(rectFBuyButton.left + maxSilkLeftW * packValueSecond - mCircular / 2f
                    , rectFBuyButton.top + mCircular / 1.4f + mCircular / 1.5f
            );
            mPathsilkLeft.lineTo(rectFBuyButton.left + maxSilkLeftW * packValueSecond - mCircular / 2f
                    , rectFBuyButton.top + mCircular / 1.4f
            );

            mPathsilkLeft.close();
            canvas.drawPath(mPathsilkLeft, mPaintBuyButton);


            float maxSilkRightW = rectFBuyButton.width() - mCircular + mCircular;


            mPathsilkRight.reset();
            mPathsilkRight.moveTo(rectFBuyButton.right, rectFBuyButton.top + mCircular / 1.4f);
            mPathsilkRight.lineTo(rectFBuyButton.right, rectFBuyButton.top + mCircular / 1.4f + mCircular / 1.5f);
            mPathsilkRight.lineTo(rectFBuyButton.right - maxSilkRightW * packValueSecond + mCircular / 2f
                    , rectFBuyButton.top + mCircular / 1.4f + mCircular / 1.5f
            );
            mPathsilkRight.lineTo(rectFBuyButton.right - maxSilkRightW * packValueSecond + mCircular / 2f
                    , rectFBuyButton.top + mCircular / 1.4f
            );

            mPathsilkRight.close();
            canvas.drawPath(mPathsilkRight, mPaintBuyButton);


            if (mAmAnimatedPackValue > 0.8f && mAmAnimatedPackValue <= 1f) {
                mPaintBuyButton.setColor(Color.rgb(254, 230, 51));
                float packValueThird = (mAmAnimatedPackValue - 0.8f) / 0.2f;


                Path bowknotLeftTop = new Path();
                bowknotLeftTop.reset();

                float x = (float) ((mCircular / 3f) * Math.cos(90 * Math.PI / 180f));
                float y = (float) ((mCircular / 3f) * Math.sin(90 * Math.PI / 180f));

                bowknotLeftTop.moveTo(rectFBuyButton.left + mCircular * 1.1f + mCircular / 3f - x,
                        rectFBuyButton.top + mCircular / 1.4f + mCircular / 3f - y);


                float x2 = (float) ((mCircular / 3f) * Math.cos(-60 * Math.PI / 180f));
                float y2 = (float) ((mCircular / 3f) * Math.sin(-60 * Math.PI / 180f));


                bowknotLeftTop.lineTo(rectFBuyButton.left + mCircular * 1.1f + mCircular / 3f - x2,
                        rectFBuyButton.top + mCircular / 1.4f + mCircular / 3f - y2);
                bowknotLeftTop.lineTo(rectFBuyButton.left + mCircular * 1.1f + mCircular / 3f - x2 - mCircular,
                        rectFBuyButton.top + mCircular / 1.4f + mCircular / 3f - y2 - mCircular / 3f);

                bowknotLeftTop.lineTo(rectFBuyButton.left + mCircular * 1.1f + mCircular / 3f - x - mCircular,
                        rectFBuyButton.top + mCircular / 1.4f + mCircular / 3f - y - mCircular / 3f);
                bowknotLeftTop.close();
                canvas.drawPath(bowknotLeftTop, mPaintBuyButton);


                Path bowknotRighrTop = new Path();
                bowknotRighrTop.reset();
                float x3 = (float) ((mCircular / 3f) * Math.cos(240 * Math.PI / 180f));
                float y3 = (float) ((mCircular / 3f) * Math.sin(240 * Math.PI / 180f));

                bowknotRighrTop.moveTo(rectFBuyButton.left + mCircular * 1.1f + mCircular / 3f - x,
                        rectFBuyButton.top + mCircular / 1.4f + mCircular / 3f - y);


                bowknotRighrTop.lineTo(rectFBuyButton.left + mCircular * 1.1f + mCircular / 3f - x3,
                        rectFBuyButton.top + mCircular / 1.4f + mCircular / 3f - y3);
                bowknotRighrTop.lineTo(rectFBuyButton.left + mCircular * 1.1f + mCircular / 3f - x3 + mCircular,
                        rectFBuyButton.top + mCircular / 1.4f + mCircular / 3f - y3 - mCircular / 3f);

                bowknotRighrTop.lineTo(rectFBuyButton.left + mCircular * 1.1f + mCircular / 3f - x + mCircular,
                        rectFBuyButton.top + mCircular / 1.4f + mCircular / 3f - y - mCircular / 3f);
                bowknotRighrTop.close();
                canvas.drawPath(bowknotRighrTop, mPaintBuyButton);


                float space = mCircular / 3 * 2 * 1.2f;
                Path bowknotLeftBottom = new Path();
                bowknotLeftBottom.reset();
                float x4 = (float) ((mCircular / 3f) * Math.cos(270 * Math.PI / 180f));
                float y4 = (float) ((mCircular / 3f) * Math.sin(270 * Math.PI / 180f));
                bowknotLeftBottom.moveTo(rectFBuyButton.left + mCircular * 1.1f + mCircular / 3f - x4,
                        rectFBuyButton.top + mCircular / 1.4f + mCircular / 3f - y4);

                bowknotLeftBottom.lineTo(rectFBuyButton.left + mCircular * 1.1f + mCircular / 3f - x4
                                - space,
                        rectFBuyButton.top + mCircular / 1.4f + mCircular / 3f - y4
                                + space);

                bowknotLeftBottom.lineTo(rectFBuyButton.left + mCircular * 1.1f + mCircular / 3f - x4
                                - space,
                        rectFBuyButton.top + mCircular / 1.4f + mCircular / 3f - y4
                                + space - space / 2);
                bowknotLeftBottom.lineTo(rectFBuyButton.left + mCircular * 1.1f + mCircular / 3f - x4
                                - space - space / 2,
                        rectFBuyButton.top + mCircular / 1.4f + mCircular / 3f - y4
                                + space - space / 2);

                bowknotLeftBottom.lineTo(rectFBuyButton.left + mCircular * 1.1f + mCircular / 3f - x4
                                - space + space,
                        rectFBuyButton.top + mCircular / 1.4f + mCircular / 3f - y4
                                + space - space / 2 - space / 2 - space);
                bowknotLeftBottom.close();
                canvas.drawPath(bowknotLeftBottom, mPaintBuyButton);


                Path bowknotRightBottom = new Path();
                bowknotRightBottom.reset();

                bowknotRightBottom.moveTo(rectFBuyButton.left + mCircular * 1.1f + mCircular / 3f - x4,
                        rectFBuyButton.top + mCircular / 1.4f + mCircular / 3f - y4);


                bowknotRightBottom.lineTo(rectFBuyButton.left + mCircular * 1.1f + mCircular / 3f - x4
                                + space,
                        rectFBuyButton.top + mCircular / 1.4f + mCircular / 3f - y4
                                + space);

                bowknotRightBottom.lineTo(rectFBuyButton.left + mCircular * 1.1f + mCircular / 3f - x4
                                + space,
                        rectFBuyButton.top + mCircular / 1.4f + mCircular / 3f - y4
                                + space - space / 2);
                bowknotRightBottom.lineTo(rectFBuyButton.left + mCircular * 1.1f + mCircular / 3f - x4
                                + space + space / 2,
                        rectFBuyButton.top + mCircular / 1.4f + mCircular / 3f - y4
                                + space - space / 2);

                bowknotRightBottom.lineTo(rectFBuyButton.left + mCircular * 1.1f + mCircular / 3f - x4
                                + space - space,
                        rectFBuyButton.top + mCircular / 1.4f + mCircular / 3f - y4
                                + space - space / 2 - space / 2 - space);
                bowknotRightBottom.close();
                canvas.drawPath(bowknotRightBottom, mPaintBuyButton);


                mPaintBuyButton.setColor(Color.GRAY);

                canvas.drawCircle(
                        rectFBuyButton.left + mCircular * 1.1f + mCircular / 3f
                        , rectFBuyButton.top + mCircular / 1.4f + mCircular / 3f,
                        (mCircular / 3f * 1.401f) * packValueThird,
                        mPaintBuyButton


                );

                mPaintBuyButton.setColor(Color.rgb(254, 230, 51));

                canvas.drawCircle(
                        rectFBuyButton.left + mCircular * 1.1f + mCircular / 3f
                        , rectFBuyButton.top + mCircular / 1.4f + mCircular / 3f,
                        (mCircular / 3f * 1.4f) * packValueThird,
                        mPaintBuyButton


                );

            }


        }


    }

    private void drawCheckOutButton(Canvas canvas) {

        if (mAnimatedBgValue > 0.5f) {
            mPaintBuyButton.setColor(checkButtonColor);
            rectFCheckButton.top = rectFBgMove.top + rectFBgMove.height() / 3f * 2f + rectFBgMove.height() / 3f / 2f -
                    mBuyButtonH
            ;
            rectFCheckButton.bottom = rectFBgMove.top + rectFBgMove.height() / 3f * 2f + rectFBgMove.height() / 3f / 2f +
                    mBuyButtonH;
            rectFCheckButton.right = rectFBgMove.right - mCircular +
                    (rectFBg.width() - mCircular * 2 - mBuyButtonW);
            rectFCheckButton.left = rectFBgMove.right - mCircular - mBuyButtonW +
                    (rectFBg.width() - mCircular * 2 - mBuyButtonW);


            if (pressCheckButton) {
                rectFCheckButton.bottom = rectFCheckButton.bottom - 4;
                rectFCheckButton.top = rectFCheckButton.top + 4;
                rectFCheckButton.left = rectFCheckButton.left + 4;
                rectFCheckButton.right = rectFCheckButton.right - 4;


            }


            canvas.drawRoundRect(rectFCheckButton, mCircular / 2f, mCircular / 2f, mPaintBuyButton);
            mPaintText.setColor(Color.WHITE);
            mPaintText.setTextSize(mCircular - 1);
            canvas.drawText(mButtonCheckText, rectFCheckButton.centerX() - getFontlength(mPaintText, mButtonCheckText) / 2f
                    , rectFCheckButton.centerY() + getFontHeight(mPaintText, mButtonCheckText) / 3f,
                    mPaintText);
        }


    }


    private void drawCardContent(Canvas canvas) {

        if (mAnimatedBgValue > 0.6f) {
            mPaintText.setTextSize(mCircular * 0.8f);
            mPaintText.setColor(Color.BLACK);
            canvas.drawText(cardTip, rectFBuyButton.left,
                    rectFBg.top + mCircular + getFontHeight(mPaintText, cardTip)
                    , mPaintText);


            if (mBuyer != null) {
                canvas.drawText(mBuyer.name, rectFBuyButton.left,
                        rectFBg.top + mCircular + getFontHeight(mPaintText, mBuyer.name) * 3
                        , mPaintText);

                mPaintText.setColor(mPriceTextColor);

                canvas.drawText(mBuyer.region, rectFBuyButton.left,
                        rectFBg.top + mCircular + getFontHeight(mPaintText, mBuyer.region) * 4
                                + getFontHeight(mPaintText, mBuyer.name) / 2
                        , mPaintText);
                canvas.drawText(mBuyer.address, rectFBuyButton.left,
                        rectFBg.top + mCircular + getFontHeight(mPaintText, mBuyer.address) * 5f
                                + getFontHeight(mPaintText, mBuyer.name) / 2
                        , mPaintText);


                canvas.drawText(mBuyer.availableDay, rectFBuyButton.left,
                        rectFBg.top + mCircular + getFontHeight(mPaintText, mBuyer.availableDay) * 8
                        , mPaintText);


            }


        }

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();

        mCircular = (getMeasuredWidth() / 20);
        rectFBg.top = mPadding;
        rectFBg.left = mPadding;
        rectFBg.bottom = getMeasuredHeight() - mPadding;
        rectFBg.right = getMeasuredWidth() - mPadding;


        drawBg(canvas);
        drawCardTopBg(canvas);
        drawCardTopBgShadow(canvas);
        drawLogo(canvas);
        drawCardBottomBg(canvas);
        drawTitleAndPrice(canvas);
        drawBuyButton(canvas);
        drawCheckOutButton(canvas);
        drawCardContent(canvas);
        canvas.restore();


    }

    public float getFontlength(Paint paint, String str) {
        Rect rect = new Rect();
        paint.getTextBounds(str, 0, str.length(), rect);
        return rect.width();
    }

    public float getFontHeight(Paint paint, String str) {
        Rect rect = new Rect();
        paint.getTextBounds(str, 0, str.length(), rect);
        return rect.height();

    }

    public int dip2px(float dpValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    protected Bitmap setBitmapSize(int iconId, int w) {


        Bitmap bitmap = BitmapFactory.decodeResource(getContext().getResources(), iconId);
        float s = w * 1.0f / bitmap.getWidth();
        bitmap = Bitmap.createScaledBitmap(bitmap, (int) (bitmap.getWidth() * s), (int) (bitmap.getHeight() * s), true);
        return bitmap;

    }

    private void setShader(Paint p, int startColor, int endColor) {
        mShader = new LinearGradient(rectFBg.left, rectFBg.top,
                rectFBg.right, rectFBg.bottom,
                new int[]{startColor, endColor

                }, new float[]{0f, 1f},
                Shader.TileMode.CLAMP);
        p.setColor(startColor);
        p.setShader(mShader);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                getParent().requestDisallowInterceptTouchEvent(false);

                if (rectFBuyButton.contains(event.getX(), event.getY()) && mAmAnimatedPackValue == 0f) {
                    pressBuyButton = true;
                } else {
                    pressBuyButton = false;
                }
                invalidate();


                break;
            case MotionEvent.ACTION_MOVE:

                if (rectFBuyButton.contains(event.getX(), event.getY()) && mAmAnimatedPackValue == 0f) {
                    pressBuyButton = true;
                    invalidate();
                } else {
                    if (pressBuyButton) {
                        pressBuyButton = false;
                        invalidate();
                    }

                }
                if (rectFCheckButton.contains(event.getX(), event.getY()) && mAnimatedBgValue == 1.0f) {
                    pressCheckButton = true;
                    invalidate();
                } else {
                    if (pressCheckButton) {
                        pressCheckButton = false;
                        invalidate();
                    }

                }


                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                getParent().requestDisallowInterceptTouchEvent(false);
                pressBuyButton = false;
                pressCheckButton = false;
                if (rectFBuyButton.contains(event.getX(), event.getY()) && mAmAnimatedPackValue == 0f) {
                    startAnim(1);
                } else if (rectFCheckButton.contains(event.getX(), event.getY()) && mAnimatedBgValue == 1.0f) {
//                    Toast.makeText(getContext(), "111", Toast.LENGTH_SHORT).show();
                    if (mOnCheckOut != null) {
                        mOnCheckOut.ok(getId());
                    } else {
                        Toast.makeText(getContext(), "OnCheckOut is null", Toast.LENGTH_SHORT).show();

                    }
                    postInvalidate();

                }


                return false;
            default:
                break;
        }
        return true;
    }


    public void startAnim(int Step) {
        if (Step == 1) {
            stopAnim();
            startViewAnim(0f, 1f, 600, Step);
        } else if (Step == 2) {
            startViewAnim(0f, 1f, 400, Step);

        }
    }

    private ValueAnimator valueAnimator;
    private float mAnimatedBgValue = 0f;
    private float mAmAnimatedPackValue = 0f;

    public void Restore() {
        if (valueAnimator != null) {
            clearAnimation();
            valueAnimator.setRepeatCount(0);
            valueAnimator.cancel();
            mAmAnimatedPackValue = 0f;
            mAnimatedBgValue = 0f;
            postInvalidate();
        }
    }

    public void stopAnim() {
        if (valueAnimator != null) {
            clearAnimation();
            valueAnimator.setRepeatCount(0);
            valueAnimator.cancel();
//            valueAnimator.end();
            mAmAnimatedPackValue = 0f;
            mAnimatedBgValue = 0f;
            postInvalidate();
        }
    }


    private ValueAnimator startViewAnim(float startF, final float endF, long time, final int Step) {
        valueAnimator = ValueAnimator.ofFloat(startF, endF);
        valueAnimator.setDuration(time);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setRepeatCount(0);
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {

//                mAnimatedBgValue = (float) valueAnimator.getAnimatedValue() ;
                if (Step == 1) {
                    mAmAnimatedPackValue = (float) valueAnimator.getAnimatedValue();
                } else if (Step == 2) {
                    mAnimatedBgValue = (float) valueAnimator.getAnimatedValue();

                }
                invalidate();
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (Step == 1) {
                    startAnim(2);
                }

            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                super.onAnimationRepeat(animation);
            }
        });
        if (!valueAnimator.isRunning()) {
            valueAnimator.start();

        }

        return valueAnimator;
    }


    public static class Buyer {

        public String name;
        public String region;

        public String address;
        public String availableDay;


        public Buyer(String name, String region, String address, String availableDay) {
            this.name = name;
            this.region = region;
            this.address = address;
            this.availableDay = availableDay;
        }


    }


    public interface OnCheckOut {
        void ok(int vid);
    }


}
