package com.vv.zvv.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;

import com.vv.zvv.Adapters.MyThreeExpandableListViewAdapter;
import com.vv.zvv.JavaBean.Classes;
import com.vv.zvv.JavaBean.College;
import com.vv.zvv.R;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * 三级联动
 */
public class ThreeExpandableListViewActivity extends AppCompatActivity {

    @ViewInject(R.id.three_view_simple)
    private ExpandableListView mExpandableListView;

    //模拟数据
    private List<College> mCollegeList;
    private List<Classes> mClassesList;

    //    MyExpandableListViewAdapter mAdapter;
    MyThreeExpandableListViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_expandable_list_view);

        x.view().inject(this);

        initView();
        initData();
        initListener();
    }

    private void initView() {
        mExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
            }
        });
    }

    private void initData() {

        //大学
        mCollegeList = new ArrayList<>();
        for (int a = 0; a < 3; a++) {
            College college = new College();
            college.setCollegeName("大学" + a + "号");

            //班级
            mClassesList = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                Classes classNames = new Classes();
                classNames.setClassName("尖刀" + i + "班");

                //学生
                List<String> studentList = new ArrayList<>();
                for (int j = 0; j < 8; j++) {
                    String studentName = i + "班逗比" + j + "号";
                    studentList.add(studentName);
                }
                classNames.setStudentList(studentList);
                mClassesList.add(classNames);
            }

            college.setClassList(mClassesList);
            mCollegeList.add(college);
        }
    }

    private void initListener() {
//        mAdapter = new MyExpandableListViewAdapter(mClassesList, ThreeExpandableListViewActivity.this);
        mAdapter = new MyThreeExpandableListViewAdapter(this, mCollegeList);
        mExpandableListView.setAdapter(mAdapter);
        for (int i = 0; i < mCollegeList.size(); i++) {
            mExpandableListView.expandGroup(i);
        }

    }
}
