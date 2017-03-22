package com.bidchat.nik.lolview;

import android.content.Context;
import android.media.Image;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class LolView extends RelativeLayout {
    private Context context;
    private ImageView mImageLetterO, mImageSmiley;
    private final int ANIMATION_TIME = 500;
    private int smileyDispositionHeight;

    public LolView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        initView();
    }

    public LolView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }

    public LolView(Context context) {
        super(context);
        this.context = context;
        initView();
    }

    private void initView() {
        View view = inflate(getContext(), R.layout.layout_lol_view, null);

        final ViewGroup rootView = (ViewGroup) view.getRootView();
        LinearLayout linearRootView = new LinearLayout(context);
        RelativeLayout.LayoutParams layoutParamsGrowShrink = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParamsGrowShrink.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        linearRootView.setLayoutParams(layoutParamsGrowShrink);
        linearRootView.setOrientation(LinearLayout.HORIZONTAL);

        // Right Letter L
        ImageView imageLeftLetter = new ImageView(context);
        LinearLayout.LayoutParams layoutParamsLeftImage = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParamsLeftImage.gravity = Gravity.CENTER_VERTICAL;
        layoutParamsLeftImage.weight = 1;
        imageLeftLetter.setLayoutParams(layoutParamsLeftImage);
        imageLeftLetter.setAdjustViewBounds(true);
        imageLeftLetter.setImageDrawable(getResources().getDrawable(R.mipmap.ic_letter_l));
        linearRootView.addView(imageLeftLetter);

        // Center Relative Holder
        RelativeLayout relativeAnimationHolder = new RelativeLayout(context);
        LinearLayout.LayoutParams layoutParamsCenterHolder = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParamsCenterHolder.weight = 1;
        relativeAnimationHolder.setLayoutParams(layoutParamsCenterHolder);

        mImageLetterO = new ImageView(context);
        RelativeLayout.LayoutParams layoutParamsLetterOImage = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParamsLetterOImage.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        mImageLetterO.setLayoutParams(layoutParamsLetterOImage);
        mImageLetterO.setAdjustViewBounds(true);
        mImageLetterO.setImageDrawable(getResources().getDrawable(R.drawable.ic_letter_o));
        mImageLetterO.setId(R.id.image_letter_O);
        relativeAnimationHolder.addView(mImageLetterO);

        mImageSmiley = new ImageView(context);
        RelativeLayout.LayoutParams layoutParamsSmiley = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParamsSmiley.addRule(RelativeLayout.ABOVE, mImageLetterO.getId());
        mImageSmiley.setLayoutParams(layoutParamsSmiley);
        mImageSmiley.setAdjustViewBounds(true);
        mImageSmiley.setImageDrawable(getResources().getDrawable(R.drawable.ic_smiley));
        relativeAnimationHolder.addView(mImageSmiley);
        linearRootView.addView(relativeAnimationHolder);

        ViewTreeObserver vtoImageLeftB = mImageSmiley.getViewTreeObserver();
        vtoImageLeftB.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                mImageSmiley.getViewTreeObserver().removeOnPreDrawListener(this);
                smileyDispositionHeight = mImageSmiley.getMeasuredHeight();
                animateLayout(smileyDispositionHeight);
                return true;
            }
        });

        // Left Letter L
        ImageView imageRightLetter = new ImageView(context);
        LinearLayout.LayoutParams layoutParamsRightImage = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParamsRightImage.gravity = Gravity.CENTER_VERTICAL;
        layoutParamsRightImage.weight = 1;
        imageRightLetter.setLayoutParams(layoutParamsRightImage);
        imageRightLetter.setAdjustViewBounds(true);
        imageRightLetter.setImageDrawable(getResources().getDrawable(R.mipmap.ic_letter_l));
        linearRootView.addView(imageRightLetter);

        rootView.addView(linearRootView);

        addView(rootView);
    }

    public void animateLayout(int translateToPosition) {
        Animation an = new TranslateAnimation(0, 0, translateToPosition, 0);//0,0 is the current       coordinates
        an.setFillAfter(true); // Needed to keep the result of the animation
        an.setDuration(ANIMATION_TIME);
        an.setRepeatCount(Animation.INFINITE);
        an.setRepeatMode(Animation.REVERSE);
        mImageSmiley.startAnimation(an);// to start animation obviously

        Animation anim = new ScaleAnimation(1, 1, 0, 1, Animation.ABSOLUTE, 0,
                Animation.RELATIVE_TO_SELF, 1);
        anim.setFillAfter(true); // Needed to keep the result of the animation
        anim.setDuration(ANIMATION_TIME);
        anim.setRepeatCount(Animation.INFINITE);
        anim.setRepeatMode(Animation.REVERSE);
        mImageLetterO.startAnimation(anim);
    }
}