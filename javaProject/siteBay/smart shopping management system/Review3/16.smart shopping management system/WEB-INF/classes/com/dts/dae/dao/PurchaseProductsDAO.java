package com.dts.dae.dao;

import com.dts.core.dao.AbstractDataAccessObject;
import com.dts.core.util.DateWrapper;
import com.dts.dae.model.PurchaseProductsBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class PurchaseProductsDAO extends AbstractDataAccessObject
{
  private boolean flag;

  public PurchaseProductsDAO()
  {
    getConnection();
  }

  public String getUniqueCartId()
  {
    String sUniqueCartId = "";
    int iMaxCount = 0;
    try
    {
      Statement st = this.con.createStatement();
      ResultSet resultSet = st.executeQuery("select count(*) from shoppingcart");
      if (resultSet.next())
        iMaxCount = Integer.parseInt(resultSet.getString(1));
      sUniqueCartId = "cart_" + (iMaxCount + 1);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      this.flag = false;
      try
      {
        this.con.rollback();
      }
      catch (SQLException se)
      {
        se.printStackTrace();
      }
    }
    return sUniqueCartId;
  }

  public boolean addtoCart(PurchaseProductsBean ppbean)
  {
    Date pdate = new Date();
    String custname = ppbean.getCustomername();
    String prodname = ppbean.getProductname();
    String quantity = ppbean.getQuantity();
    String purchaseddate = DateWrapper.parseDate(pdate);
    String cartid = getUniqueCartId();
    try
    {
      PreparedStatement pst = null;
      int i = 0;
	  System.out.println("before executing the preparestatement ");
      pst = this.con.prepareStatement("insert into shoppingcart values(?,?,?,?,?,?)");
      pst.setString(6, cartid);
      pst.setString(1, custname);
      pst.setString(2, prodname);
      pst.setString(3, quantity);
      pst.setString(4, purchaseddate);
      pst.setString(5, "No");
      i = pst.executeUpdate();
      if (i == 1)
      {
        this.flag = true;
        this.con.commit();
      }
      else {
        this.flag = false;
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
      this.flag = false;
      try
      {
        this.con.rollback();
      }
      catch (SQLException se)
      {
        se.printStackTrace();
      }
    }
    try
    {
      this.con.close();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    try
    {
      this.con.close();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    try
    {
      this.con.close();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return this.flag;
  }
}