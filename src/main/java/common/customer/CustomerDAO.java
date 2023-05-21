package common.customer;

import java.util.List;
import jdbc.JdbcTemplate;
import jdbc.RowMapper;

public class CustomerDAO {
  public void addCustomer(Customer customer) {
    JdbcTemplate template = new JdbcTemplate();
    String sql = "insert into CUSTOMERS values(?,?,?,?,?,?,?,?)";
    template.executeUpdate(sql, customer.getBankAccount(), customer.getBirth(), customer.getFamilyHistory(), customer.isGender(), customer.getHealthExaminationRecord(), customer.getJob(), customer.getName());
  }

  public Customer findByCustomerId(String customerId) {
    RowMapper<Customer> rm = rs ->
        new Customer(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getBoolean("gender"),
            rs.getString("birth"),
            rs.getString("job"),
            rs.getString("bank_account"),
            rs.getString("family_history"),
            rs.getString("health_examination_record")
        );

    JdbcTemplate template = new JdbcTemplate();
    String sql = "select * from CUSTOMERS where id = ?";
    return template.executeQuery(sql, rm, customerId);
  }

  public void removeCustomer(String customerId) {
    JdbcTemplate template = new JdbcTemplate();
    String sql = "delete from CUSTOMERS where id = ?";
    template.executeUpdate(sql, customerId);
  }

  public void updateCustomer(Customer customer) {
    JdbcTemplate template = new JdbcTemplate();
    String sql = "update CUSTOMERS set name = ?, gender = ?, birth = ?, job = ?, bank_account = ?, family_history = ?, health_examination_record = ? where id = ?";
    template.executeUpdate(sql,
        customer.getName(), customer.getBirth(), customer.getJob(),
        customer.getBankAccount(), customer.getFamilyHistory(), customer.getHealthExaminationRecord(),
        customer.getCustomerId()
    );
  }

  public List<Customer> findCustomers() {
    RowMapper<Customer> rm = rs ->
        new Customer(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getBoolean("gender"),
            rs.getString("birth"),
            rs.getString("job"),
            rs.getString("bakn_account"),
            rs.getString("family_history"),
            rs.getString("health_examination_record")
        );

    JdbcTemplate template = new JdbcTemplate();
    String sql = "select * from CUSTOMERS";
    return template.list(sql, rm);
  }
}
