package ejiang.online.publicutils.views;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import ejiang.online.publicutils.R;


public class DetermineAndCancelDialog extends Dialog {
    private Context context;
    /**
     * 确定按钮
     */
    private TextView tvConfirm;
    /**
     * 取消按钮
     */
    private TextView tvCancel;
    /**
     * 标题按钮
     */
    private TextView tvTitle;
    /**
     * 弹出框内容
     */
    private TextView dialog_text;

    public DetermineAndCancelDialog(@NonNull Context context) {
        super(context);
        this.context=context;
        init();
        initListener();
    }

    public DetermineAndCancelDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.context=context;
        init();
        initListener();
    }

    protected DetermineAndCancelDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.context=context;
        init();
        initListener();
    }

    private void initListener() {
        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clickListener!=null){
                    clickListener.doConfirm();
                }

            }
        });
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetermineAndCancelDialog.this.dismiss();
            }
        });
    }

    private void init() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_determine_cancel, null);
        setContentView(view);

        tvTitle = (TextView) view.findViewById(R.id.title);
        dialog_text = (TextView) view.findViewById(R.id.dialog_text);
        tvConfirm = (TextView) view.findViewById(R.id.dialog_determine);
        tvCancel = (TextView) view.findViewById(R.id.dialog_cancel);
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = context.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        lp.width = (int) (d.widthPixels * 1); // 高度设置为屏幕的0.6
        dialogWindow.setAttributes(lp);
    }

    /**
     * 是否显示弹框内容
     *
     */
    public void isShowContent(boolean show, String content) {
        if(show){
            dialog_text.setVisibility(View.VISIBLE);
            if(!TextUtils.isEmpty(content)){
                dialog_text.setText(content);
            }
        }
    }
    /**
     * 是否显示取消按钮
     */
    public void isShowCancle(boolean show, String content) {
        if(show){
            tvCancel.setVisibility(View.VISIBLE);
            if(!TextUtils.isEmpty(content)){
                tvCancel.setText(content);
            }
        }
    }
    /**
     * 是否显示确定按钮
     */
    public void isShowDetermine(boolean show, String content) {
        if(show){
            tvConfirm.setVisibility(View.VISIBLE);
            if(!TextUtils.isEmpty(content)){
                tvConfirm.setText(content);
            }
        }
    }
    public void setDialogButtonListener(DialogButtonListener clickListener) {
        this.clickListener = clickListener;
    }

    private DialogButtonListener clickListener;




    public interface DialogButtonListener {

        public void doConfirm();

        public void doCancel();
    }
}
