package com.authorhub.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.authorhub.R;
import com.authorhub.adapters.BookAdapter;
import com.authorhub.models.BookModel;
import com.authorhub.models.SliderItem;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import com.authorhub.adapters.The_Slide_items_Pager_Adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

public class HomeFragment1 extends Fragment {

    ListView listView;

    String[] strBook = {"Book 1", "Book 2", "Book 3", "Book 4", "Book 5", "Book 6", "Book 7", "Book 8"};

    int[] imgBook = {R.drawable.ic_home, R.drawable.ic_home, R.drawable.ic_home,
            R.drawable.ic_home, R.drawable.ic_home, R.drawable.ic_home, R.drawable.ic_home, R.drawable.ic_home};

    ArrayList<BookModel> bookModelArrayList;

    private List<SliderItem> listItems;
    private ViewPager page;
    private TabLayout tabLayout;

    /*Slider Code*/
    String strImag[]= {"bithday","anniversary gift hamper"," couple hamper","gift box","surpise"};

    int imgData[] = {R.drawable.slideimg1,R.drawable.slideimg2,
            R.drawable.slideimg3,R.drawable.slideimg4,R.drawable.slidimg5};
    private ArrayList<SliderItem> sliderItemArrayList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_home1, container, false);
        listView = rootView.findViewById(R.id.list_view);
        bookModelArrayList = new ArrayList<BookModel>();

        for (int i = 0; i < strBook.length; i++) {
            BookModel bookModel = new BookModel(strBook[i], imgBook[i]);
            bookModelArrayList.add(bookModel);
        }

        BookAdapter bookAdapter = new BookAdapter(getActivity(),bookModelArrayList);
        listView.setAdapter(bookAdapter);

        page = rootView.findViewById(R.id.my_pager) ;
        tabLayout = rootView.findViewById(R.id.my_tablayout);

        // Make a copy of the slides you'll be presenting.
        listItems = new ArrayList<SliderItem>() ;


        for (int i = 0; strImag.length > i; i++) {
            SliderItem sliderItem = new SliderItem(strImag[i], imgData[i]);
            listItems.add(sliderItem);
        }

        The_Slide_items_Pager_Adapter itemsPager_adapter = new The_Slide_items_Pager_Adapter(getActivity(), listItems);
        page.setAdapter(itemsPager_adapter);

        // The_slide_timer
        java.util.Timer timer = new java.util.Timer();
        timer.scheduleAtFixedRate(new The_slide_timer(),2000,3000);
        tabLayout.setupWithViewPager(page,true);

        return rootView;
    }


    private class The_slide_timer extends TimerTask {
        @Override
        public void run() {

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (page.getCurrentItem()< listItems.size()-1) {
                        page.setCurrentItem(page.getCurrentItem()+1);
                    }
                    else
                        page.setCurrentItem(0);
                }
            });
        }
    }
}