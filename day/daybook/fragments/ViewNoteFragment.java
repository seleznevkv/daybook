package com.elysey.daybook.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.elysey.daybook.MainActivity;
import com.elysey.daybook.R;
import com.elysey.daybook.base.BaseUtil;
import com.elysey.daybook.model.Note;

public class ViewNoteFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_view_note, container, false);

        initToolbar();
        initView(v);

        return v;
    }

    private void initView(View v) {
        Note note = BaseUtil.getNote(getActivity(), getArguments().getInt(BaseUtil.NOTE_ID) + "");

        TextView tvNoteTitle = (TextView) v.findViewById(R.id.tvNoteTitle);
        TextView tvNoteDate = (TextView) v.findViewById(R.id.tvNoteDate);
        TextView tvNoteTime = (TextView) v.findViewById(R.id.tvNoteTime);
        TextView tvNoteText = (TextView) v.findViewById(R.id.tvNoteText);

        tvNoteTitle.setText(note.titleDay);
        tvNoteTime.setText(note.timeDay);
        tvNoteDate.setText(note.dateDay);
        if (!note.noteDay.equals(""))
            tvNoteText.setText(note.noteDay);
        else
            tvNoteText.setText("<Пустая заметка>");
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_view, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.deleteNote) {

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Удалить данную заметку?")
                    .setCancelable(true)
                    .setPositiveButton("Удалить", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            BaseUtil.deleteRow(getActivity(), getArguments().getInt(BaseUtil.NOTE_ID) + "");

                            ListFragment listFragment = (ListFragment) ((MainActivity) getActivity()).getSupportFragmentManager().findFragmentByTag("listNote");
                            listFragment.reloadList();

                            ((MainActivity) getActivity()).showSnackbar("Заметка удалена");

                            dialog.cancel();
                            getActivity().onBackPressed();
                        }
                    })
                    .setNegativeButton("ОТМЕНА",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

            AlertDialog alert = builder.create();
            alert.show();


            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void initToolbar() {
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(getArguments().getString("title"));
        ((MainActivity) getActivity()).setVisibleFab(false);

        setHasOptionsMenu(true);

    }

    @Override
    public void onDestroy() {
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(getActivity().getResources().getString(R.string.app_name));
        ((MainActivity) getActivity()).setVisibleFab(true);

        super.onDestroy();
    }


}
