package com.raselahmed.kidztool.application_layer;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.raselahmed.kidztool.R;

public class FeedbackActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editName, editSubject, editMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        editName = findViewById(R.id.editNameId);
        editSubject = findViewById(R.id.editSubId);
        editMsg = findViewById(R.id.editMsgId);
        Button submitBtn = findViewById(R.id.submitBtnId);
        submitBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.submitBtnId) {
            takeFeedback();
        }
    }

    private void takeFeedback() {

        String name = editName.getText().toString().trim();
        String subject = editSubject.getText().toString().trim();
        String message = editMsg.getText().toString().trim();

        if (TextUtils.isEmpty(name)) {
            editName.setError("Mandatory Field");
        }
        if (TextUtils.isEmpty(subject)) {
            editSubject.setError("Mandatory Field");
        }
        if (TextUtils.isEmpty(message)) {
            editMsg.setError("Mandatory Field");
        }

        Intent i = new Intent(Intent.ACTION_SENDTO);
        i.setData(Uri.parse("mailto:"));
        i.putExtra(Intent.EXTRA_EMAIL, new String[]{"azmaan.amin.35@gmail.com"});
        i.putExtra(Intent.EXTRA_SUBJECT, subject);
        i.putExtra(Intent.EXTRA_TEXT, "Dear Admin, \n\n" + message + "\n\n    -- Regards " + name);
        try {
            startActivity(Intent.createChooser(i, "send mail"));
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "No Mail App Found", Toast.LENGTH_SHORT).show();
        } catch (Exception ex) {
            Toast.makeText(this, "Unexpected Error..!!", Toast.LENGTH_SHORT).show();
        }

    }
}
