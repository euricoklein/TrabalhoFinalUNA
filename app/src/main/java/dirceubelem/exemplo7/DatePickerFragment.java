package dirceubelem.exemplo7;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * Created by Welbert on 05/04/2015.
 */
public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    public DatePickerFragmentListener _DatePickerFragmentListener;
    int _year, _mounth, _day;

    public static DatePickerFragment getDatePicker(DatePickerFragmentListener listener) {
        DatePickerFragment d = new DatePickerFragment();
        d._DatePickerFragmentListener = listener;
        return d;

    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user
        _year = year;
        _mounth = month;
        _day = day;

        _DatePickerFragmentListener.onDateSelected(_year, _mounth, _day);


    }

    public String ReturnsSelectedDate() {

        return new StringBuilder().append(_day).append("/").append(_mounth).append("/").append(_year).toString();
    }

    public interface DatePickerFragmentListener {
        void onDateSelected(int ano, int mes, int dia);
    }
}