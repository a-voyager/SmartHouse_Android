package top.wuhaojie.smarthouse.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import top.wuhaojie.smarthouse.R;

/**
 * Created by wuhaojie on 2016/7/8 14:42.
 */
public class SwitchView extends CardView {

    private Context mContext;
    private ImageView mIvImg;
    private TextView mTvTitle;

    private boolean mIsOpen = false;
    private Drawable mOnDrawable;
    private Drawable mOffDrawable;

    public interface OnSwitchListener {
        void onSwitch(boolean currIsOpen);
    }

    private OnSwitchListener mOnSwitchListener;

    public SwitchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView(attrs, defStyleAttr);
    }

    public SwitchView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwitchView(Context context) {
        this(context, null, 0);
    }

    private void initView(AttributeSet attrs, int defStyleAttr) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.switch_item, this);

        View rootView = view.findViewById(R.id.item_root);
        mIvImg = (ImageView) view.findViewById(R.id.iv_item_img);
        mTvTitle = (TextView) view.findViewById(R.id.tv_item_title);

        setClickable(false);

        rootView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mIsOpen = !mIsOpen;
                refreshImg();
                mOnSwitchListener.onSwitch(mIsOpen);
            }
        });

        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.SwitchView, defStyleAttr, 0);

        String title = typedArray.getString(R.styleable.SwitchView_cardTitle);
        Drawable onDrawable = typedArray.getDrawable(R.styleable.SwitchView_onResId);
        Drawable offDrawable = typedArray.getDrawable(R.styleable.SwitchView_offResId);

        setView(offDrawable, onDrawable, title);

        typedArray.recycle();


    }

    private void refreshImg() {
        if (mIsOpen) {
            mIvImg.setImageDrawable(mOnDrawable);
        } else {
            mIvImg.setImageDrawable(mOffDrawable);
        }
    }

    private void setView(Drawable offRes, Drawable onRes, String text) {
        mOffDrawable = offRes;
        mOnDrawable = onRes;
        refreshImg();
        mTvTitle.setText(text);
    }

    public void setOnSwitchListener(OnSwitchListener onSwitchListener) {
        mOnSwitchListener = onSwitchListener;
    }

    public boolean isOpen() {
        return mIsOpen;
    }


    public void setOpen(boolean open) {
        mIsOpen = open;
    }
}
