package bornbaby.com.schooldemo.helper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import bornbaby.com.schooldemo.R;
import bornbaby.com.schooldemo.model.Student;

/**
 * Created by madhu on 28-Dec-17.
 */

public class StudentsAdapter extends BaseAdapter {
    private ArrayList<Student> studentsList;
    private Context context;

    private LayoutInflater layoutInflater;


    public StudentsAdapter(ArrayList<Student> studentsList, Context context) {
        this.studentsList = studentsList;
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return studentsList.size();
    }

    @Override
    public Object getItem(int position) {
        return studentsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_item, null);
            viewHolder = new ViewHolder();
            convertView.setTag(viewHolder);
            viewHolder.name = convertView.findViewById(R.id.tv_student_name);
            viewHolder.email = convertView.findViewById(R.id.tv_student_email);
            viewHolder.iv_user = convertView.findViewById(R.id.iv_user);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();

        }
        Student student = studentsList.get(position);
        viewHolder.name.setText(student.getName());
        viewHolder.email.setText(student.getEmail());

        if (student.getUserPic() != null) {
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            bmOptions.inJustDecodeBounds = false;
            bmOptions.inSampleSize = 10;
            bmOptions.inPurgeable = true;
            Bitmap bitmapPhoto = BitmapFactory.decodeFile(student.getUserPic(), bmOptions);
            viewHolder.iv_user.setImageBitmap(bitmapPhoto);
        }
        return convertView;
    }

    class ViewHolder {
        private TextView name;
        private TextView email;
        private ImageView iv_user;

    }
}
