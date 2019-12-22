<%@include file="header.jsp" %>
<style>
    body {
        font-family: Arial;
        font-size: 17px;
        padding: 8px;
    }

    * {
        box-sizing: border-box;
    }

    .row {
        display: -ms-flexbox; /* IE10 */
        display: flex;
        -ms-flex-wrap: wrap; /* IE10 */
        flex-wrap: wrap;
        margin: 0 -16px;
    }

    .col-25 {
        -ms-flex: 25%; /* IE10 */
        flex: 25%;
    }

    .col-50 {
        -ms-flex: 50%; /* IE10 */
        flex: 50%;
    }

    .col-75 {
        -ms-flex: 75%; /* IE10 */
        flex: 75%;
    }

    .col-25,
    .col-50,
    .col-75 {
        padding: 0 30px;
    }

    .container-test {
        background-color: #f2f2f2;
        padding: 5px 20px 15px 20px;
        border: 1px solid lightgrey;
        border-radius: 3px;
    }

    input[type="text"] {
        width: 100%;
        margin-bottom: 20px;
        padding: 12px;
        border: 1px solid #ccc;
        border-radius: 3px;
    }

    label {
        margin-bottom: 10px;
        display: block;
    }

    .icon-container {
        margin-bottom: 20px;
        padding: 7px 0;
        font-size: 24px;
    }

    .btn {
        background-color: #4caf50;
        color: white;
        padding: 12px;
        margin: 10px 0;
        border: none;
        width: 100%;
        border-radius: 3px;
        cursor: pointer;
        font-size: 17px;
    }

    .btn:hover {
        background-color: #45a049;
    }

    a {
        color: #2196f3;
    }

    hr {
        border: 1px solid lightgrey;
    }

    span.price {
        float: right;
        color: grey;
    }

    /* Responsive layout - when the screen is less than 800px wide, make the two columns stack on top of each other instead of next to each other (also change the direction - make the "cart" column go on top) */
    @media (max-width: 800px) {
        .row {
            flex-direction: column-reverse;
        }

        .col-25 {
            margin-bottom: 20px;
        }
    }
</style>
<section>
    <div class="row">
        <div class="col-75">
            <div class="container-test">
                <form id="myForm" method="post">
                    <div class="row">
                        <div class="col-50">
                            <h3>Billing Address</h3>
                            <label for="fname"><i class="fa fa-user"></i> Full Name</label>
                            <input
                                    type="text"
                                    id="fname"
                                    name="firstname"
                                    placeholder="John M. Doe"
                                    value="${LoginUser.userName}"
                                    required
                            />
                            <label for="email"><i class="fa fa-envelope"></i> Email</label>
                            <input
                                    type="text"
                                    id="email"
                                    name="email"
                                    placeholder="john@example.com"
                                    value="${LoginUser.email}"
                                    required
                            />
                            <label for="email"><i class="fa fa-phone"></i> Phone</label>
                            <input
                                    type="text"
                                    id="phone"
                                    name="phone"
                                    placeholder="0858798182"
                                    value="${LoginUser.phoneNumber}"
                                    required
                            />
                            <label for="adr"
                            ><i class="fa fa-address-card-o"></i> Address</label
                            >
                            <input
                                    type="text"
                                    id="adr"
                                    name="address"
                                    placeholder="542 W. 15th Street"
                                    value="${LoginUser.address}"
                                    required
                            />
                            <label for="province"
                            ><i class="fa fa-institution"></i> Province</label
                            >
                            <input
                                    type="text"
                                    id="province"
                                    name="Province"
                                    placeholder="Ha Noi"
                            />
                            <label for="district"
                            ><i class="fa fa-institution"></i> District</label
                            >
                            <input
                                    type="text"
                                    id="district"
                                    name="District"
                                    placeholder="Quan Thanh Xuan"
                            />
                        </div>
                        <div class="col-50">
                            <h3>Payment</h3>
                            <div>
                                <input
                                        type="radio"
                                        name="data"
                                        onclick="paymentNormal()"
                                        id="paymentNormal"
                                />
                                Receive Money when order done <br/>
                                <input
                                        type="radio"
                                        name="data"
                                        onclick="paymentCard()"
                                        id="paymentCard"
                                />
                                Payment by credit card <br/>
                            </div>
                            <div id="cardPayment" style="display: none">
                                <label for="fname">Accepted Cards</label>
                                <div class="icon-container">
                                    <i class="fa fa-cc-visa" style="color:navy;"></i>
                                    <i class="fa fa-cc-amex" style="color:blue;"></i>
                                    <i class="fa fa-cc-mastercard" style="color:red;"></i>
                                    <i class="fa fa-cc-discover" style="color:orange;"></i>
                                </div>
                                <label for="cname">Name on Card</label>
                                <input
                                        type="text"
                                        id="cname"
                                        name="cardname"
                                        placeholder="John More Doe"
                                />
                                <label for="ccnum">Credit card number</label>
                                <input
                                        type="text"
                                        id="ccnum"
                                        name="cardnumber"
                                        placeholder="1111-2222-3333-4444"
                                />
                                <label for="expmonth">Exp Month</label>
                                <input
                                        type="text"
                                        id="expmonth"
                                        name="expmonth"
                                        placeholder="September"
                                />
                                <div class="row">
                                    <div class="col-50">
                                        <label for="expyear">Exp Year</label>
                                        <input
                                                type="text"
                                                id="expyear"
                                                name="expyear"
                                                placeholder="2018"
                                        />
                                    </div>
                                    <div class="col-50">
                                        <label for="cvv">CVV</label>
                                        <input type="text" id="cvv" name="cvv" placeholder="352"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <label>
                        <input type="checkbox" checked="checked" name="sameadr"/> Shipping
                        address same as billing
                    </label>
                    <input
                            type="submit"
                            value="Continue to checkout"
                            id="button-submit"
                            class="btn"
                    >
                        Continue to checkout
                </form>
            </div>
        </div>
    </div>
</section>
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
<script type="text/javascript">
    function paymentNormal() {
        $("#cardPayment").style.display = "none";
    }

    function paymentCard() {
        $("#cardPayment").style.display = "block";
    }
    $(document).ready(function () {
        $("#myForm").on('submit', function(e){
            e.preventDefault();
            var addressOrder =
                $("#adr").val() +
                ", " +
                $("#district").val() +
                ", " +
                $("#province").val();
            console.log(addressOrder);
            $.ajax({
                url: "Pay", //servlet url
                type: "POST",
                data: {address: addressOrder},
                success: data => {
                console.log(data);
            if (data.redirect) {
                // data.redirect contains the string URL to redirect to
                window.location.href = data.redirect;
            } else {
                showNotification("Order Product Success", "success");
            }
        }
        })
            ;
        });
    });

</script>

<%@include file="footer.jsp" %>
