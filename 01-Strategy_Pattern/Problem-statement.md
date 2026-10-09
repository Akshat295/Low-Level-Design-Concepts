# Strategy Pattern — Problem Description

## Problem

We are building an e-commerce application that supports multiple payment methods.

Currently, the application supports:

- Credit Card
- UPI
- Cash

The `PaymentService` is responsible for processing the payment.

Initially, the developer implements the payment logic using `if-else` conditions.

Later, the business wants to add more payment methods such as:

- PayPal
- Net Banking
- Wallet
- Apple Pay

The question is:

> How can we design the payment system so that adding a new payment method does not require continuously modifying the existing `PaymentService`?

## Expected Requirements

1. The application should support multiple payment methods.
2. Each payment method can have its own payment logic.
3. Adding a new payment method should require minimal changes to existing code.
4. `PaymentService` should not contain the implementation details of every payment method.

## Goal

Design the system in a way that allows us to **change the payment algorithm/behavior independently from the class that uses it**.