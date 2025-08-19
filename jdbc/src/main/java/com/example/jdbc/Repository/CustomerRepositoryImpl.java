package com.example.jdbc.Repository;




import com.example.jdbc.Entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

    @Repository
    public class CustomerRepositoryImpl implements CustomerRepository {

        @Autowired
        private JdbcTemplate jdbcTemplate;

        private Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
            Customer c = new Customer();
            c.setId(rs.getLong("id"));
            c.setName(rs.getString("name"));
            c.setEmail(rs.getString("email"));
            c.setStatus(rs.getString("status"));
            return c;
        }

        @Override
        public int save(Customer customer) {
            String sql = "INSERT INTO customers(name, email, status) VALUES (?, ?, ?)";
            try {
                return jdbcTemplate.update(sql, customer.getName(), customer.getEmail(), customer.getStatus());
            } catch (DataAccessException e) {
                throw new RuntimeException("Error inserting customer", e);
            }
        }

        @Override
        public int update(Customer customer) {
            String sql = "UPDATE customers SET name=?, email=?, status=? WHERE id=?";
            try {
                return jdbcTemplate.update(sql, customer.getName(), customer.getEmail(), customer.getStatus(), customer.getId());
            } catch (DataAccessException e) {
                throw new RuntimeException("Error updating customer", e);
            }
        }

        @Override
        public int deleteById(Long id) {
            String sql = "DELETE FROM customers WHERE id=?";
            try {
                return jdbcTemplate.update(sql, id);
            } catch (DataAccessException e) {
                throw new RuntimeException("Error deleting customer", e);
            }
        }

        @Override
        public Optional<Customer> findById(Long id) {
            String sql = "SELECT * FROM customers WHERE id=?";
            try {
                return jdbcTemplate.query(sql, this::mapRow, id).stream().findFirst();
            } catch (DataAccessException e) {
                throw new RuntimeException("Error fetching customer by ID", e);
            }
        }

        @Override
        public List<Customer> findAll() {
            String sql = "SELECT * FROM customers";
            try {
                return jdbcTemplate.query(sql, this::mapRow);
            } catch (DataAccessException e) {
                throw new RuntimeException("Error fetching all customers", e);
            }
        }
    }


