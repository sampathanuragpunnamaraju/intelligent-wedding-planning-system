# Rulebook - Venue

# Wedding Venue Rulebook

## Purpose

The Venue Rule Engine evaluates whether a selected venue or location is suitable for the planned wedding.

The engine considers:

* Guest Count
* Budget
* Location
* Weather
* Accessibility
* Parking
* Venue Capacity

---

## VR-001 Venue Type Selection

Small Hall

1 - 100 Guests

---

Banquet Hall

101 - 300 Guests

---

Large Banquet Hall

301 - 600 Guests

---

Convention Center

601 - 1000 Guests

---

Wedding Grounds

More than 1000 Guests

---

## VR-002 Capacity Validation

Formula

Required Capacity = Guest Count × 1.2

Condition

Venue Capacity < Required Capacity

Result

Venue Rejected

---

Example

Guests = 500

Required Capacity = 600

Venue Capacity = 450

Result = Not Feasible

---

## VR-003 Budget Compatibility

Condition

Expected Venue Cost > Allocated Venue Budget

Result

Budget Risk Detected

---

Budget Categories

Small Hall

₹50,000 - ₹2,00,000

---

Banquet Hall

₹2,00,000 - ₹6,00,000

---

Large Banquet Hall

₹5,00,000 - ₹15,00,000

---

Convention Center

₹10,00,000+

---

## VR-004 Indoor vs Outdoor Suitability

Condition

Outdoor Venue

AND

Rain Probability > 60%

Result

Outdoor Venue Not Recommended

---

Alternative Suggestions

Indoor Hall

Covered Lawn

Hybrid Venue

---

## VR-005 Peak Season Analysis

Peak Wedding Season

November

December

January

February

---

Result

Higher Venue Costs

Lower Availability

Advance Booking Recommended

---

## VR-006 Accessibility Evaluation

Evaluate

Road Connectivity

Railway Access

Airport Access

---

Output

Good

Moderate

Poor

---

## VR-007 Parking Validation

Formula

Required Parking = Guest Count ÷ 4

Condition

Available Parking < Required Parking

Result

Parking Risk Detected

---

## VR-008 Weather Suitability Score

Factors

Temperature

Humidity

Rain Probability

---

Output

0 - 100

Weather Suitability Score

---

Excellent

80 - 100

---

Good

60 - 79

---

Moderate

40 - 59

---

Poor

Below 40

---

## VR-009 Location Feasibility

Evaluate

Budget

Guest Count

Location Costs

Venue Availability

---

Output

Excellent

Good

Moderate

Poor

---

## VR-010 Location Suggestions

Condition

Location Not Selected

Result

Suggest Locations Based On

Budget

Guest Count

Weather

Availability

---

Example Suggestions

Hyderabad Outskirts

Vijayawada

Visakhapatnam

Based On Budget And Requirements
