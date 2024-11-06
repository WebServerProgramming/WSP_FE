package com.example.front.main;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.front.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class RoomCreateActivity extends AppCompatActivity {
    private static final int PICK_IMAGE = 1; // 이미지 선택 요청 코드
    private static final int REQUEST_PERMISSION = 100; // 권한 요청 코드
    private CircleImageView ivRoomCreate1; // 선택한 이미지를 표시할 ImageView
    private EditText etRoomCreate1;
    private EditText etRoomCreate2;
    private Button btnRoomCreate;

    private boolean isEt1Filled = false;
    private boolean isEt2Filled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_creation);

        ivRoomCreate1 = findViewById(R.id.iv_room_create_1); // XML에서 ImageView 찾기
        etRoomCreate1 = findViewById(R.id.et_room_create_1);
        etRoomCreate2 = findViewById(R.id.et_room_create_2);
        btnRoomCreate = findViewById(R.id.btn_room_create);

        ivRoomCreate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 권한 요청 및 갤러리 열기
                checkPermission();
            }
        });

        btnRoomCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RoomCreateActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // TextWatcher를 통해 입력 필드 확인
        etRoomCreate1.addTextChangedListener(textWatcher);
        etRoomCreate2.addTextChangedListener(textWatcher);
        checkAllFields();
    }

    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_IMAGES) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_MEDIA_IMAGES}, REQUEST_PERMISSION);
        } else {
            openGallery(); // 권한이 이미 부여된 경우 갤러리 열기
        }
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE); // 갤러리 열기
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData(); // 선택한 이미지의 URI

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri); // URI를 Bitmap으로 변환
                ivRoomCreate1.setImageBitmap(bitmap); // ImageView에 이미지 설정
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "이미지를 불러오는 데 실패했습니다.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGallery(); // 권한이 부여된 경우 갤러리 열기
            } else {
                Toast.makeText(this, "권한이 거부되었습니다.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            isEt1Filled = !etRoomCreate1.getText().toString().trim().isEmpty();
            isEt2Filled = !etRoomCreate2.getText().toString().trim().isEmpty();
            checkAllFields();
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    private void checkAllFields() {
        if (isEt1Filled && isEt2Filled) {
            btnRoomCreate.setEnabled(true);
        } else {
            btnRoomCreate.setEnabled(false);
        }
    }
}
