package tw.com.bluemoon.androidtest101_01;

import android.widget.TextView;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

import mockit.Mocked;
import mockit.Verifications;

import static mockit.Deencapsulation.invoke;

/**
 * Created by Chris on 2015/7/17.
 */
public class AverageTest {
    private Average average;
    @Mocked
    TextView mockTextView;

    private ArrayList<Person> getDataList01() {
        ArrayList<Person> arrayList = new ArrayList<>();
        arrayList.add(new Person(200));
        arrayList.add(new Person(100));
        return arrayList;
    }

    private ArrayList<Person> getDataList02() {
        ArrayList<Person> arrayList = new ArrayList<>();
        arrayList.add(new Person(50));
        arrayList.add(new Person(25));
        arrayList.add(new Person(25));
        return arrayList;
    }


    //新增測試
    @Test(dependsOnMethods = "testGetAVG_Result_150_0")
    public void testNotifyDataSetChanged_Result_150() throws Exception {
        average = new Average(getDataList01(), mockTextView);
        average.notifyDataSetChanged();

        new Verifications() {{
            mockTextView.setText("150");
        }};
    }
    //原本測試
    @Test
    public void testGetAVG_Result_150_0() throws Exception {
        average = new Average(getDataList01(), mockTextView);

        Assert.assertEquals(invoke(average, "getAVG"), 150.0);
    }
    //新增測試
    @Test
    public void testGetAVG_Result_33_33333206176758() throws Exception {
        average = new Average(getDataList02(), mockTextView);

        Assert.assertEquals(invoke(average, "getAVG"), 33.33333206176758);
    }

    //新增測試
    @Test(dependsOnMethods = "testGetAVG_Result_33_33333206176758")
    public void testNotifyDataSetChangedResult33_333() throws Exception {
        average = new Average(getDataList02(), mockTextView);
        average.notifyDataSetChanged();

        new Verifications() {{
            mockTextView.setText("33.333");
        }};
    }
}