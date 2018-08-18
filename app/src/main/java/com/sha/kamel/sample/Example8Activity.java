package com.sha.kamel.sample;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.sha.kamel.formvalidator.ValidationManager;
import com.sha.kamel.formvalidator.validator.ConditionalEmailValidator;
import com.sha.kamel.formvalidator.validator.FixedLengthValidator;
import com.sha.kamel.formvalidator.validator.RangeValidator;
import com.sha.kamel.sample.validator.MobileValidator;

import butterknife.BindView;
import butterknife.OnClick;

public class Example8Activity extends BaseExampleActivity {

    @BindView(R.id.checkBox)
    CheckBox checkBox;

    @BindView(R.id.et_email)
    EditText et_email;

    @BindView(R.id.til_email)
    TextInputLayout til_email;

    private ValidationManager formValidator;

    protected int resourceLayout(){
        return R.layout.activity_content_7;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        formValidator
                .add(
                        new ConditionalEmailValidator(et_email, text -> checkBox.isChecked()),
                        new RangeValidator(et_name, 4, 100),
                        new FixedLengthValidator(et_age, 2),
                        new MobileValidator(et_mobile),
                        new RangeValidator(et_area, 3, 25)
                )
                .doIfInvalid(() -> toast("Fill required data."))
                .subscribe(() -> toast("Saved data successfully."));
    }

    @OnClick({
            R.id.btn_submit,
            R.id.checkBox
    })
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_submit:
                formValidator.startValidation();
                break;

            case R.id.checkBox:
                til_email.setVisibility(checkBox.isChecked() ? View.VISIBLE : View.GONE);
                break;
        }
    }

}

