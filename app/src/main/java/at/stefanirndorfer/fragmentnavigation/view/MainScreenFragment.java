package at.stefanirndorfer.fragmentnavigation.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import at.stefanirndorfer.fragmentnavigation.R;
import at.stefanirndorfer.fragmentnavigation.input.NavigationListener;
import at.stefanirndorfer.fragmentnavigation.viewmodel.MainScreenViewModel;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainScreenFragment extends Fragment {

    private MainScreenViewModel mViewModel;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.bt_fragment_a)
    Button navButtonA;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.bt_fragment_b)
    Button navButtonB;
    private NavigationListener navigationListener;


    public static MainScreenFragment newInstance() {
        return new MainScreenFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_screen_fragment, container, false);
        ButterKnife.bind(this, view);
        navigationListener.hideHomeButton();
        return view;
    }

    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
        try {
            navigationListener = (NavigationListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement NavigationListener");
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MainScreenViewModel.class);
        // TODO: Use the ViewModel
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.bt_fragment_a)
    public void navToFragmentA() {
        navigationListener.navigateToFragmentA();
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.bt_fragment_b)
    public void navToFragmentB() {
        navigationListener.navigateToFragmentB();
    }

}