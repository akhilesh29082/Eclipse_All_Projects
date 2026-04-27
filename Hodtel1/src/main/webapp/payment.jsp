<%@ include file="navbar.jsp" %>
<link rel="stylesheet" href="style.css">

<div class="main">
    <div class="container">

        <h2>Fee Payment</h2>

        <form action="PaymentServlet" method="post">
            <input type="number" name="studentId" placeholder="Student ID" required>
            <input type="number" name="amount" placeholder="Amount" required>

            <select name="mode">
                <option>Cash</option>
                <option>UPI</option>
                <option>Card</option>
            </select>

            <button type="submit">Pay</button>
        </form>

        <hr>

        <h3>Download Receipt</h3>

        <form action="downloadReceipt" method="get">
            <input type="number" name="studentId" placeholder="Student ID" required>
            <button type="submit">Download Receipt</button>
        </form>

    </div>
</div>