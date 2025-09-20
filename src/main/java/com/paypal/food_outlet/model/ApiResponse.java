package com.paypal.food_outlet.model;

import java.util.List;

public class ApiResponse {
    public int page;
    public int per_page;
    public int total;
    public int total_pages;
    public List<Outlet> data;
}
