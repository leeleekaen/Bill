package bill.zts.com.bill.ui.fragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import bill.zts.com.bill.R;
import bill.zts.com.bill.ui.adapter.DataAdapter;
import bill.zts.com.bill.ui.adapter.EditBillAdapter;
import bill.zts.com.bill.ui.domain.AddBillBean;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.lujun.androidtagview.TagContainerLayout;
import co.lujun.androidtagview.TagView;
import mvp.zts.com.mvp_base.utils.SnackbarUtil;

/**
 * Created by Administrator on 2016/7/27.
 */
public class EditBillDialogFragment extends DialogFragment {

    @Bind(R.id.edit_bill_noTv)
    TextView noTv;
    @Bind(R.id.edit_bill_yesTv)
    TextView yesTv;
    @Bind(R.id.edit_bill_addB_img)
    ImageView addB_img;
    @Bind(R.id.edit_bill_addTag_img)
    ImageView addTag_img;
    @Bind(R.id.edit_bill_edit)
    EditText editMoney;
    @Bind(R.id.edit_bill_addTag)
    TagContainerLayout addTag;
    @Bind(R.id.edit_bill_defaultTag)
    TagContainerLayout defaultTag;
    @Bind(R.id.edit_bill_RecyclerView)
    RecyclerView mRecyclerView;

    private Context mContext;
    private EditBillAdapter mEditBillAdapter;
    private List<AddBillBean> billList = new ArrayList<AddBillBean>();

    private String[] str1 = {"jiu","米油","盐","醋茶","柴酱醋茶"};
    private String[] str2 = {"柴酱醋茶","柴酱醋茶","米油","盐","醋茶","柴酱醋茶","柴酱醋茶","米油","盐","醋茶","柴酱醋茶"};

    public interface DialogFragmentDataImp{//定义一个与Activity通信的接口，使用该DialogFragment的Activity须实现该接口
        void showMessage(String message);
    }

    public static EditBillDialogFragment newInstance(String message){
        //创建一个带有参数的Fragment实例
        EditBillDialogFragment fragment = new EditBillDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("message", message);
        fragment.setArguments(bundle);//把参数传递给该DialogFragment
        return fragment;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View customView = LayoutInflater.from(getActivity()).inflate(
                R.layout.fragment_edit_bill_dialog, null);
        ButterKnife.bind(this,customView);
        mContext = getActivity();

        initView();

        return new AlertDialog.Builder(getActivity()).setView(customView)
                .create();
    }

    private void initView() {
        initRecycleView();

        addTag.setTags(str1);
        defaultTag.setTags(str2);

        addTag.setOnTagClickListener(new TagView.OnTagClickListener() {

            @Override
            public void onTagClick(int position, String text) {
                addTag.removeTag(position);
            }

            @Override
            public void onTagLongClick(final int position, String text) {

            }
        });
        defaultTag.setOnTagClickListener(new TagView.OnTagClickListener() {

            @Override
            public void onTagClick(int position, String text) {
                addTag.addTag(text);
            }

            @Override
            public void onTagLongClick(final int position, String text) {

            }
        });

        //mTvMsg.setText(getArguments().getString("message"));//把传递过来的数据设置给TextView


    }
    private void initRecycleView() {
        mRecyclerView.setHasFixedSize(false);
        LinearLayoutManager llm = new LinearLayoutManager(mContext);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);
        mEditBillAdapter = new EditBillAdapter(mContext,billList);
        mRecyclerView.setAdapter(mEditBillAdapter);
    }


    @OnClick(R.id.edit_bill_yesTv) void yesTv() {
        dismiss();
    }
    @OnClick(R.id.edit_bill_noTv) void noTv() {
        //DialogFragmentDataImp imp = (DialogFragmentDataImp) getActivity();
        //imp.showMessage(getArguments().getString("message"));//对话框与Activity间通信，传递数据给实现了DialogFragmentDataImp接口的Activity
        dismiss();
    }
    @OnClick(R.id.edit_bill_addB_img) void addB_img() {

        if(TextUtils.isEmpty(editMoney.getText())){
            SnackbarUtil.PrimarySnackbar(mContext,noTv,"   你的Money不能为空!!!");
        }else {
            AddBillBean addBillBean = new AddBillBean();
            addBillBean.setStrMoney(editMoney.getText()+"");
            addBillBean.setTagList(addTag.getTags());
            mEditBillAdapter.insertedItem(addBillBean);
        }
    }

}
