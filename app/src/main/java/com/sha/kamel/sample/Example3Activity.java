package com.sha.kamel.sample;

import android.os.Bundle;
import android.util.Log;

import com.sha.kamel.formvalidator.validator.FixedLengthValidator;
import com.sha.kamel.formvalidator.validator.RangeValidator;
import com.sha.kamel.sample.validator.MobileValidator;

public class Example3Activity extends BaseExampleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        formValidator.with(btn_submit)
                .doIfInvalid(() -> toast("Fill required data."))
                .add(
                        new RangeValidator(et_name, 4, 100),
                        new FixedLengthValidator(et_age, 2),
                        new MobileValidator(et_mobile),
                        new RangeValidator(et_area, 3, 25))
                .map(validator -> new ClientInfo().setArea(validator.textOf(et_area)))
                .doIfInvalid(() -> toast("Form is invalid."))
                .messageIfEmpty("Field is empty.")
                .asObservable()
                .doOnNext(data -> Log.d(getClass().getSimpleName(), "Saving Client info"))
//               .flatMap(data -> {}) --> save in database
//               .flatMap(data -> {}) --> send to server
                .subscribe(
                        data -> toast("Saved data successfully."),
                        Throwable::printStackTrace);
    }

}

