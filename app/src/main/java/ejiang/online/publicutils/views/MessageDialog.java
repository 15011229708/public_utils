package ejiang.online.publicutils.views;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import ejiang.online.publicutils.R;


public class MessageDialog extends Dialog {
    private Window window = null;
    private Context context;
    private TextView text;
    private String content = "";
    TextView yes;
    TextView no;
    private onCancelListener onCancelListener;//取消按钮被点击了的监听器
    private onSubmitListener onSubmitListener;//确定按钮被点击了的监听器


    public MessageDialog(@NonNull Context context, String content) {
        super(context);
        this.context = context;
        this.content = content;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //初始化布局R.layout.imagedialogview
        View loadingview= LayoutInflater.from(getContext()).inflate(R.layout.message_dialog,null);
        text = (TextView) loadingview.findViewById(R.id.text);
        text.setText(content);
        yes = (TextView) loadingview.findViewById(R.id.yes);
        no = (TextView) loadingview.findViewById(R.id.no);
        //设置dialog的布局
        setContentView(loadingview);
        //设置window背景，默认的背景会有Padding值，不能全屏。当然不一定要是透明，你可以设置其他背景，替换默认的背景即可。
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //一定要在setContentView之后调用，否则无效
        getWindow().setLayout((int) (getWindow().getWindowManager().getDefaultDisplay().getWidth()*0.8), ViewGroup.LayoutParams.WRAP_CONTENT);
        super.onCreate(savedInstanceState);
        //初始化界面控件的事件
        initEvent();
    }

    /**
     * 设置取消按钮的显示内容和监听
     *
     * @param onCancelListener
     */
    public void setCancelListener(onCancelListener onCancelListener) {
        this.onCancelListener = onCancelListener;
    }

    /**
     * 设置确定按钮的显示内容和监听
     *
     * @param onSubmitListener
     */
    public void setSubmitListener(onSubmitListener onSubmitListener) {
        this.onSubmitListener = onSubmitListener;
    }

    /**
     * 初始化界面的确定和取消监听器
     */
    private void initEvent() {
        //设置确定按钮被点击后，向外界提供监听
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onSubmitListener != null) {
                    onSubmitListener.onSubmit();
                }
            }
        });
        //设置取消按钮被点击后，向外界提供监听
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onCancelListener != null) {
                    onCancelListener.onCancel();
                }
            }
        });
    }

    /**
     * 设置确定按钮和取消被点击的接口
     */
    public interface onCancelListener {
        void onCancel();
    }

    public interface onSubmitListener {
        void onSubmit();
    }
    public interface setCanceledOnTouchOutside {
        void setCanceledOnTouchOutside();
    }


    //设置窗口显示
    public void windowDeploy(int x, int y){
        window = getWindow(); //得到对话框
        window.setWindowAnimations(R.style.AlertDialogStyle); //设置窗口弹出动画
        window.setBackgroundDrawableResource(R.color.transparent); //设置对话框背景为透明
        WindowManager.LayoutParams wl = window.getAttributes();
        //根据x，y坐标设置窗口需要显示的位置
        wl.x = x; //x小于0左移，大于0右移
        wl.y = y; //y小于0上移，大于0下移
//            wl.alpha = 0.6f; //设置透明度
//            wl.gravity = Gravity.BOTTOM; //设置重力
        window.setAttributes(wl);
    }
    @Override
    public void show() {
        //设置触摸对话框意外的地方取消对话框
        setCanceledOnTouchOutside(true);
        super.show();
    }
    public void show(boolean outside){
        if(outside){
            setCanceledOnTouchOutside(true);
        }else {
            setCanceledOnTouchOutside(false);
        }
        super.show();
    }
    @Override
    public void dismiss() {
        super.dismiss();
    }
}
