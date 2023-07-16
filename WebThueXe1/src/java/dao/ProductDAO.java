/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import entites.Product;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class ProductDAO extends DBContext {

    public List<Product> getAllProduct(int page, int pageSize) {
        List<Product> productList = new ArrayList<>();

        try {
            // Calculate the offset based on the page number and page size
            int offset = (page - 1) * pageSize;

            // Create SQL query with LIMIT and OFFSET clauses
            String query = "SELECT *\n"
                    + "FROM products\n"
                    + "WHERE status  = 0\n"
                    + "ORDER BY id\n"
                    + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";

            // Create prepared statement
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, pageSize);
            statement.setInt(2, offset);

            // Execute the query
            ResultSet resultSet = statement.executeQuery();

            // Iterate over the result set
            while (resultSet.next()) {
                // Retrieve product details from each row
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
//                int quantity = resultSet.getInt("quantity");
                String image = resultSet.getString("image");
                int category = resultSet.getInt("category_id");
                String description = resultSet.getString("description");

                // Create a Product object with the retrieved data
                Product product = new Product(id, name, price, category, image, description);

                // Add the product to the list
                productList.add(product);
            }

            // Close the resources
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return productList;
    }

    public Product getProductByID(int productId) {
        Product product = null;

        try {
            String query = "SELECT p.id, p.name, p.price, p.category_id, p.image,\n"
                    + "description, c.name as category_name FROM products p \n"
                    + "					join  categories c\n"
                    + "					on p.category_id = c.id where p.id =? ";
            // Create prepared statement
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, productId);

            // Execute the query
            ResultSet resultSet = statement.executeQuery();

            // Check if a matching product is found
            if (resultSet.next()) {
                // Retrieve product details from the result set
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                String image = resultSet.getString("image");
                int category = resultSet.getInt("category_id");
                String description = resultSet.getString("description");
                String category_name = resultSet.getString("category_name");

                // Create a Product object with the retrieved data
                product = new Product(id, name, price, category, image, description, category_name);
            }

            // Close the resources
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return product;
    }

    public List<Product> searchProductsWithConditions(String searchParam, String categoryParam, String sortOption) {
        List<Product> productList = new ArrayList<>();
        List<Object> list = new ArrayList<>();
        try {
            StringBuilder query = new StringBuilder();
            query.append("SELECT * FROM products WHERE status = 0 ");
            // Create SQL query with sorting, LIMIT, and OFFSET clauses
            if (searchParam != null && !searchParam.trim().isEmpty()) {
                query.append(" and name LIKE ? ");
                list.add("%" + searchParam + "%");
            }
            if (categoryParam != null && !categoryParam.trim().isEmpty()) {
                query.append(" AND category_id = ? ");
                list.add(Integer.parseInt(categoryParam));
            }
            query.append(" order by ");
            switch (sortOption) {
                case "asc":
                    query.append("price ASC");
                    break;
                case "desc":
                    query.append("price DESC");
                    break;
                case "new":
                    query.append("id DESC");
                    break;
                case "sale":
                    query.append("quantity DESC");
                    break;
                default:
                    query.append("id ASC");
                    break;
            }
            // Create prepared statement
            PreparedStatement statement = connection.prepareStatement(query.toString());
            mapParams(statement, list);
            // Execute the query
            ResultSet resultSet = statement.executeQuery();
            // Iterate over the result set
            while (resultSet.next()) {
                // Retrieve product details from each row
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
//                String quantity = resultSet.getInt("quantity");
                String image = resultSet.getString("image");
                int category = resultSet.getInt("category_id");
                String description = resultSet.getString("description");

                // Create a Product object with the retrieved data
                Product product = new Product(id, name, price, category, image, description);

                // Add the product to the list
                productList.add(product);
            }
            // Close the resources
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return productList;
    }

    public boolean cancelRent(int pid) {
        try ( PreparedStatement stmt = connection.prepareStatement("UPDATE products  SET status = 0 WHERE id = ?")) {
            stmt.setInt(1, pid);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean Rent(int pid) {
        try ( PreparedStatement stmt = connection.prepareStatement("UPDATE products  SET status = 1 WHERE id = ?")) {
            stmt.setInt(1, pid);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public int getTotalProductCount() {
        int count = 0;
        try (
                 PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(*) AS count FROM products");  ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public List<Product> getProductsToManage(int page, int pageSize) {
        List<Product> products = new ArrayList<>();
        int offset = (page - 1) * pageSize;
        try (
                 PreparedStatement stmt = connection.prepareStatement("SELECT p.id, p.name, p.price, p.image, c.name AS category, p.description, p.status\n"
                        + "FROM products p\n"
                        + "JOIN categories c ON p.category_id = c.id\n"
                        + "ORDER BY p.id\n"
                        + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;")) {
            stmt.setInt(1, offset);
            stmt.setInt(2, pageSize);
            try ( ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product();
                    product.setId(rs.getInt("id"));
                    product.setName(rs.getString("name"));
                    product.setPrice(rs.getDouble("price"));
                    product.setCategoryName(rs.getString("category"));
                    product.setImage(rs.getString("image"));
                    product.setDescription(rs.getString("description"));
                    product.setStatus(rs.getInt("status"));
                    products.add(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public void mapParams(PreparedStatement ps, List<Object> args) throws SQLException {
        int i = 1;
        for (Object arg : args) {
            if (arg instanceof Date) {
                ps.setTimestamp(i++, new Timestamp(((Date) arg).getTime()));
            } else if (arg instanceof Integer) {
                ps.setInt(i++, (Integer) arg);
            } else if (arg instanceof Long) {
                ps.setLong(i++, (Long) arg);
            } else if (arg instanceof Double) {
                ps.setDouble(i++, (Double) arg);
            } else if (arg instanceof Float) {
                ps.setFloat(i++, (Float) arg);
            } else {
                ps.setString(i++, (String) arg);
            }
        }
    }

    public List<Product> Paging(List<Product> list, int pageParam, int size) {
        if (list.isEmpty()) {
            return list;
        }
        return list.subList((pageParam - 1) * size, size * pageParam >= list.size() ? list.size() : size * pageParam);
    }

    public boolean deleteProduct(int productId) {

        PreparedStatement statement = null;

        try {
            // Get a database connection

            // Create the SQL query
            String query = "DELETE FROM products WHERE id = ?";

            // Create the prepared statement
            statement = connection.prepareStatement(query);

            // Set the parameter values
            statement.setInt(1, productId);

            // Execute the query
            int rowsDeleted = statement.executeUpdate();

            // Check if any rows were deleted
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any database errors here
        } finally {
            // Close the statement and connection
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return false;
    }

    public boolean updateProduct(Product product) {
        PreparedStatement statement = null;

        try {
            // Create the SQL query
            String query = "UPDATE products SET name = ?, price = ?, category_id  = ?, image = ?, description = ? WHERE id = ?";

            // Create the prepared statement
            statement = connection.prepareStatement(query);

            // Set the parameter values
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setInt(3, product.getCategory());

            statement.setString(4, product.getImage());
            statement.setString(5, product.getDescription());
            statement.setInt(6, product.getId());

            // Execute the query
            int rowsUpdated = statement.executeUpdate();

            // Check if any rows were updated
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any database errors here
        } finally {
            // Close the statement and connection
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return false;
    }

    public boolean addProduct(Product product) {
        PreparedStatement statement = null;

        try {
            // Create the SQL query
            String query = "INSERT INTO products (name, price, image, category_id, description) VALUES (?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(query);

// Set the parameter values
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
//            statement.setInt(3, product.getQuantity());
            statement.setString(3, product.getImage());
            statement.setInt(4, product.getCategory());
            statement.setString(5, product.getDescription());

// Execute the query
            int rowsInserted = statement.executeUpdate();

            // Check if any rows were inserted
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any database errors here
        } finally {
            // Close the statement and connection
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        ProductDAO dao = new ProductDAO();
//        List<Product> list = dao.getProductsToManage(2, 2);
//        for (Product product : list) {
//            System.out.println(product);
//        }
//        Product p = new Product(1, "haha", 1, 1, 1, "hah", "haha");
//        boolean a = dao.updateProduct(p);
//        System.out.println(a);
    }
}
