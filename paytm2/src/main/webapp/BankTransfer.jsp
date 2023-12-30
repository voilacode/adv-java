<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .card {
            max-width: 400px;
            margin: 50px auto;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        .card-header {
            background-color: #007bff;
            color: #fff;
        }
        .btn-send {
            background-color: #007bff;
            color: #fff;
        }
        .btn-send:hover {
            background-color: #0056b3;
        }
    </style>
    <title>Money Transfer</title>
</head>
<body>

    <div class="card">
        <div class="card-header text-center">
            <h5 class="mb-0">Money Transfer</h5>
        </div>
        <div class="card-body">
            <form action="BankTransfer" method="get">
                <div class="mb-3">
                    <label for="senderAccount" class="form-label">Sender's Account Number</label>
                    <input type="text" class="form-control" id="senderAccount" name="senderAccount" required>
                </div>
                <div class="mb-3">
                    <label for="receiverAccount" class="form-label">Receiver's Account Number</label>
                    <input type="text" class="form-control" id="receiverAccount" name="receiverAccount" required>
                </div>
                <div class="mb-3">
                    <label for="amount" class="form-label">Amount</label>
                    <input type="text" class="form-control" id="amount" name="amount" required>
                </div>
                <button type="submit" class="btn btn-send">Send</button>
            </form>
        </div>
    </div>

    <!-- Bootstrap JS (optional) -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-e4flD4ovYyoN9Vbw8YDKaE8a+k5X7x1r2H3sJ0eLQU5rJMRdSmVME79uHlGoJ60" crossorigin="anonymous"></script>

</body>
</html>
