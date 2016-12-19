package com.elysey.daybook.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.elysey.daybook.MainActivity;
import com.elysey.daybook.R;
import com.elysey.daybook.base.BaseUtil;
import com.elysey.daybook.model.Note;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment {

    RecyclerView rvListNote;
    NotesAdapter rvAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list, container, false);


        rvListNote = (RecyclerView) v.findViewById(R.id.rvListNote);
        rvListNote.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rvListNote.setLayoutManager(llm);

        List<Note> notes = new ArrayList<>();

        rvAdapter = new NotesAdapter();
        rvListNote.setAdapter(rvAdapter);

        ArrayList<Note> notesBase = BaseUtil.getNotes(getActivity(), BaseUtil.NAME_BASE);

        rvAdapter.notes = notesBase;


        return v;
    }


    private class ViewHolder extends RecyclerView.ViewHolder {
        CardView cvNote;
        TextView tvDayTitle, tvDayDate, tvDayTime;

        public ViewHolder(View itemView) {
            super(itemView);
            cvNote = (CardView) itemView.findViewById(R.id.cvNote);
            tvDayTitle = (TextView) itemView.findViewById(R.id.tvDayTitle);
            tvDayDate = (TextView) itemView.findViewById(R.id.tvDayDate);
            tvDayTime = (TextView) itemView.findViewById(R.id.tvDayTime);
        }
    }

    public class NotesAdapter extends RecyclerView.Adapter<ViewHolder> {
        private ArrayList<Note> notes = new ArrayList<Note>();

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);

            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            holder.tvDayTitle.setText(notes.get(position).titleDay);
            holder.tvDayDate.setText(notes.get(position).dateDay);
            holder.tvDayTime.setText(notes.get(position).timeDay);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Fragment fragment = new ViewNoteFragment();
                    Bundle bundle = new Bundle();

                    bundle.putString(BaseUtil.NOTE_TITLE, holder.tvDayTitle.getText().toString());
                    bundle.putInt(BaseUtil.NOTE_ID, notes.get(position).idNote);

                    fragment.setArguments(bundle);

                    ((MainActivity) getActivity()).setFragment(fragment);
                }
            });
        }

        @Override
        public int getItemCount() {
            return notes.size();
        }
    }


    public void reloadList (){
        rvAdapter.notes =  BaseUtil.getNotes(getActivity(), BaseUtil.NAME_BASE);
        rvAdapter.notifyDataSetChanged();
    }
}
