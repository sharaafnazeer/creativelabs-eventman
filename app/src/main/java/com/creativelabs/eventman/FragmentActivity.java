package com.creativelabs.eventman;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.creativelabs.eventman.fragments.FirstFragment;
import com.creativelabs.eventman.fragments.SecondFragment;
import com.creativelabs.eventman.fragments.ThirdFragment;

public class FragmentActivity extends AppCompatActivity implements View.OnClickListener {
    FragmentManager fragmentManager;
    Button btnFirstFragment, btnSecondFragment, btnThirdFragment;

    FirstFragment firstFragment;
    SecondFragment secondFragment = new SecondFragment();
    ThirdFragment thirdFragment = new ThirdFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        btnFirstFragment = findViewById(R.id.btnFirstFragment);
        btnSecondFragment = findViewById(R.id.btnSecondFragment);
        btnThirdFragment = findViewById(R.id.btnThirdFragment);
        addFragment();

        btnFirstFragment.setOnClickListener(this);
        btnSecondFragment.setOnClickListener(this);
        btnThirdFragment.setOnClickListener(this);
    }

    private void addFragment() {
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setReorderingAllowed(true);
        firstFragment = new FirstFragment();
        Bundle bundle = new Bundle();
        bundle.putString("NAME", "My First Fragment");
        firstFragment.setArguments(bundle);
        fragmentTransaction.add(R.id.flFragment, firstFragment, null);
        fragmentTransaction.commit();
    }

    private void removeFragment() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(firstFragment);
        fragmentTransaction.commit();
    }

    private void changeFragment(int index) {
        Bundle bundle = new Bundle();
        switch (index) {
            case 0:
                bundle.putString("NAME", "My First Fragment");
                firstFragment.setArguments(bundle);
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.slide_in, R.anim.slide_out)
                        .replace(R.id.flFragment, firstFragment).commit();
                break;
            case 1:
                bundle.putString("NAME", "My Second Fragment");
                secondFragment.setArguments(bundle);
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.slide_in, R.anim.slide_out)
                        .replace(R.id.flFragment, secondFragment).commit();
                break;
            case 2:
                bundle.putString("NAME", "My Third Fragment");
                thirdFragment.setArguments(bundle);
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.slide_in, R.anim.slide_out)
                        .replace(R.id.flFragment, thirdFragment).commit();
                break;
            default:
                bundle.putString("NAME", "My First Fragment");
                firstFragment.setArguments(bundle);
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.slide_in, R.anim.slide_out)
                        .replace(R.id.flFragment, firstFragment).commit();
                break;
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnFirstFragment) {
            changeFragment(0);
        } else if (v.getId() == R.id.btnSecondFragment) {
            changeFragment(1);
        } else {
            changeFragment(2);
        }
    }
}
