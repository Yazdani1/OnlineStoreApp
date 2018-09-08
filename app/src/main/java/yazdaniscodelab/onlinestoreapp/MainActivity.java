package yazdaniscodelab.onlinestoreapp;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import java.util.PriorityQueue;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    private AllFragment allFragment;
    private CatOneFragment catOneFragment;
    private CatTwoFragment catTwoFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        allFragment=new AllFragment();
        catOneFragment=new CatOneFragment();
        catTwoFragment=new CatTwoFragment();

        setFragment(allFragment);

        bottomNavigationView=findViewById(R.id.bottombar);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.all:
                        bottomNavigationView.setItemBackgroundResource(R.color.allbottom);

                        setFragment(allFragment);

                        return true;

                    case R.id.catone:
                        bottomNavigationView.setItemBackgroundResource(R.color.catone);

                        setFragment(catOneFragment);

                        return true;

                    case R.id.cattwo:
                        bottomNavigationView.setItemBackgroundResource(R.color.cattwo);
                        setFragment(catTwoFragment);
                        return true;

                        default:
                            return false;

                }


            }
        });



    }

    public void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.mainframe,fragment);
        fragmentTransaction.commit();
    }


}
