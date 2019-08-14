package ca.judacribz.week3weekend_threads.list;

import android.content.Intent;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Locale;

import ca.judacribz.week3weekend_threads.R;
import ca.judacribz.week3weekend_threads.models.Employee;
import ca.judacribz.week3weekend_threads.list.EmployeeAdapter.*;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeHolder> {
    public static final String EXTRA_EMPLOYEE =
            "ca.judacribz.week3weekend_threads.list.EXTRA_EMPLOYEE";
    List<Employee> employees;

    public EmployeeAdapter(List<Employee> employees) {
        this.employees = employees;
    }

    @NonNull
    @Override
    public EmployeeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EmployeeHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_employee,
                parent,
                false
        ));
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeHolder holder, int position) {
        holder.setTextViews(employees.get(position));
    }

    @Override
    public int getItemCount() {
        return employees.size();
    }

    class EmployeeHolder extends RecyclerView.ViewHolder {
        TextView
                tvTaxId,
                tvName,
                tvPosition,
                tvDepartment;
        Resources resources;
        Employee employee;

        EmployeeHolder(@NonNull View itemView) {
            super(itemView);
            resources = itemView.getContext().getResources();
            tvTaxId = itemView.findViewById(R.id.tvTaxId);
            tvName = itemView.findViewById(R.id.tvName);
            tvPosition = itemView.findViewById(R.id.tvPosition);
            tvDepartment = itemView.findViewById(R.id.tvDepartment);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), EmployeeDetailsActivity.class);
                    intent.putExtra(EXTRA_EMPLOYEE, employee);
                    view.getContext().startActivity(intent);
                }
            });
        }

        void setTextViews(Employee employee) {
            this.employee = employee;
            tvTaxId.setText(employee.getTaxId());
            tvName.setText(String.format(
                    Locale.US,
                    resources.getString(R.string.name),
                    employee.getLastName(),
                    employee.getFirstName()
            ));
            tvPosition.setText(employee.getPosition());
            tvDepartment.setText(employee.getDepartment());
        }
    }
}
