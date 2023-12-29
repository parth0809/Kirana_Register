<h3>USING SPRINGBOOT AND MONGODB DATABASE FOR CREATING BACKEND API TO REGISTER AND RECORD TRANSACTION OF KIRANA STORE </h3>

<h5>Prerequisite :</h5>
 * JDK installed on your computer <br> * MongoDB installed on your computer<br>

 <h5>API ENDPOINTS : </h5>
  1 :) localhost:8585/transaction/ <br>
  a ) method:POST<br>
  {<br>
    "prod_id": "2717",<br>
    "cust_id": "102",<br>
    "price": 27.99,<br>
    "quantity": 1,<br>
    "method": "DEBIT",<br>
    "currency": "INR"<br>
  }<br>
  Values Supported: <br>prod_id(String):An id representing the product which is being sold.
  <br>cust_id(String): Used id or Shokeeper _id 
  <br>price (double): representing the cost of the product <br>
  quantity (integer) : Number of product that is being sold <br>
  method (String): CREDIT / DEBIT<br>
  currenct (STRING): supported = INR / USD<br><br>
  b) method:GET<br>
  Get all transaction records in a array of format<br>
  {<br>
    "prod_id": "2716",<br>
    "cust_id": "102",<br>
    "date": "2023/12/29",<br>
    "price": 29.99,<br>
    "currency": "USD",<br>
    "quantity": 1,<br>
    "method": "DEBIT"<br>
  },<br>
  2 :) localhost:8585/transaction/user_id/<br>
  Get all transaction assoiated with cust_id = user_id provided in header in an array of transactions .<br>
  {<br>
    "prod_id": "2716",<br>
    "cust_id": "102",<br>
    "date": "2023/12/29",<br>
    "price": 29.99,<br>
    "currency": "USD",<br>
    "quantity": 1,<br>
    "method": "DEBIT"<br>
  },<br>
  3 :) localhost:8585/transaction/user_id_and_date/<br>
  Get all transaction data assoiated with cust_id = user_id on date = date provided in header in an array of transactions .<br>
  {<br>
    "prod_id": "2716",<br>
    "cust_id": "102",<br>
    "date": "2023/12/29",<br>
    "price": 29.99,<br>
    "currency": "USD",<br>
    "quantity": 1,<br>
    "method": "DEBIT"<br>
  },<br>
   4: )localhost:8585/transaction/user_idT/<br>
  Get net transaction data assoiated with cust_id = user_id provided in header in an array of transactions .<br>
  {<br>
  "total_profit_in_usd": "-29.65 $",<br>
  "total_credit_in_usd": "0.67 $",<br>
  "total_debit_in_usd": "30.33 $",<br>
  "total_debit_in_inr": "Rs. 2519.37",<br>
  "total_credit_in_inr": "Rs. 55.98",<br>
  "cust_id": "102",<br>
  "total_profit_in_inr": "Rs. -2463.39"<br>
}<br>
  5 :) localhost:8585/transaction/user_id_and_dateT/<br>
  Get net transaction data assoiated with cust_id = user_id date = dateprovided in header in an array of transactions .<br>
{<br>
  "total_profit_in_usd": "-29.65 $",<br>
  "total_credit_in_usd": "0.67 $",<br>
  "total_debit_in_usd": "30.33 $",<br>
  "total_debit_in_inr": "Rs. 2521.61",<br>
  "total_credit_in_inr": "Rs. 55.98",<br>
  "cust_id": "102",<br>
  "total_profit_in_inr": "Rs. -2465.63",<br>
  "Date": "2023/12/29"<br>
}

<h2>MAKE SURE TO ENTER SOME DATA BEFORE USAGE OF OTHER ENDPOITS</h2>
 
