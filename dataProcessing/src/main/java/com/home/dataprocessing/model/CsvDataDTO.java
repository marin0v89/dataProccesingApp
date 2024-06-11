package com.home.dataprocessing.model;

import com.home.dataprocessing.util.DataConverter;
import com.home.dataprocessing.util.JsonToMapConverter;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;

import java.util.Map;

public class CsvDataDTO {
    @CsvBindByName(column = "_id")
    private String id;

    @CsvBindByName(column = "billing_account_id")
    private String billingAccountId;

    @CsvBindByName(column = "cost")
    private Double cost;

    @CsvCustomBindByName(column = "cost_at_list", converter = DataConverter.class)
    private Double costAtList;

    @CsvBindByName(column = "cost_type")
    private String costType;

    @CsvCustomBindByName(column = "credits.amount", converter = DataConverter.class)
    private Double creditsAmount;

    @CsvBindByName(column = "credits.full_name")
    private String creditsFullName;

    @CsvBindByName(column = "credits.id")
    private String creditsId;

    @CsvBindByName(column = "credits.name")
    private String creditsName;

    @CsvBindByName(column = "credits.type")
    private String creditsType;

    @CsvBindByName(column = "currency")
    private String currency;

    @CsvBindByName(column = "currency_conversion_rate")
    private Integer currencyConversionRate;

    @CsvBindByName(column = "invoice.month")
    private String invoiceMonth;

    @CsvCustomBindByName(column = "labels", converter = JsonToMapConverter.class)
    private Map<String, String> labels;

    @CsvBindByName(column = "location.country")
    private String locationCountry;

    @CsvBindByName(column = "location.location")
    private String locationLocation;

    @CsvBindByName(column = "location.region")
    private String locationRegion;

    @CsvBindByName(column = "location.zone")
    private String locationZone;

    @CsvCustomBindByName(column = "price.effective_price", converter = DataConverter.class)
    private Double priceEffectivePrice;

    @CsvCustomBindByName(column = "price.pricing_unit_quantity", converter = DataConverter.class)
    private Double pricePricingUnitQuantity;

    @CsvCustomBindByName(column = "price.tier_start_amount", converter = DataConverter.class)
    private Double priceTierStartAmount;

    @CsvBindByName(column = "price.unit")
    private String priceUnit;

    @CsvBindByName(column = "project.id")
    private String projectId;

    @CsvBindByName(column = "resource.global_name")
    private String resourceGlobalName;

    @CsvBindByName(column = "resource.name")
    private String resourceName;

    @CsvBindByName(column = "service.description")
    private String serviceDescription;

    @CsvBindByName(column = "service.id")
    private String serviceId;

    @CsvBindByName(column = "sku.description")
    private String skuDescription;

    @CsvBindByName(column = "sku.id")
    private String skuId;

    @CsvBindByName(column = "system_labels.key")
    private String systemLabelsKey;

    @CsvBindByName(column = "system_labels.value")
    private String systemLabelsValue;

    @CsvBindByName(column = "transaction_type")
    private String transactionType;

    @CsvBindByName(column = "usage.amount")
    private Long usageAmount;

    @CsvBindByName(column = "usage.amount_in_pricing_units")
    private Double usageAmountInPricingUnits;

    @CsvBindByName(column = "usage.pricing_unit")
    private String usagePricingUnit;

    @CsvBindByName(column = "usage.unit")
    private String usageUnit;

    @CsvBindByName(column = "usage_end_time")
    private String usageEndTime;

    @CsvBindByName(column = "usage_start_time")
    private String usageStartTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBillingAccountId() {
        return billingAccountId;
    }

    public void setBillingAccountId(String billingAccountId) {
        this.billingAccountId = billingAccountId;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Double getCostAtList() {
        return costAtList;
    }

    public void setCostAtList(Double costAtList) {
        this.costAtList = costAtList;
    }

    public String getCostType() {
        return costType;
    }

    public void setCostType(String costType) {
        this.costType = costType;
    }

    public Double getCreditsAmount() {
        return creditsAmount;
    }

    public void setCreditsAmount(Double creditsAmount) {
        this.creditsAmount = creditsAmount;
    }

    public String getCreditsFullName() {
        return creditsFullName;
    }

    public void setCreditsFullName(String creditsFullName) {
        this.creditsFullName = creditsFullName;
    }

    public String getCreditsId() {
        return creditsId;
    }

    public void setCreditsId(String creditsId) {
        this.creditsId = creditsId;
    }

    public String getCreditsName() {
        return creditsName;
    }

    public void setCreditsName(String creditsName) {
        this.creditsName = creditsName;
    }

    public String getCreditsType() {
        return creditsType;
    }

    public void setCreditsType(String creditsType) {
        this.creditsType = creditsType;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getCurrencyConversionRate() {
        return currencyConversionRate;
    }

    public void setCurrencyConversionRate(Integer currencyConversionRate) {
        this.currencyConversionRate = currencyConversionRate;
    }

    public String getInvoiceMonth() {
        return invoiceMonth;
    }

    public void setInvoiceMonth(String invoiceMonth) {
        this.invoiceMonth = invoiceMonth;
    }

    public Map<String, String> getLabels() {
        return labels;
    }

    public void setLabels(Map<String, String> labels) {
        this.labels = labels;
    }

    public String getLocationCountry() {
        return locationCountry;
    }

    public void setLocationCountry(String locationCountry) {
        this.locationCountry = locationCountry;
    }

    public String getLocationLocation() {
        return locationLocation;
    }

    public void setLocationLocation(String locationLocation) {
        this.locationLocation = locationLocation;
    }

    public String getLocationRegion() {
        return locationRegion;
    }

    public void setLocationRegion(String locationRegion) {
        this.locationRegion = locationRegion;
    }

    public String getLocationZone() {
        return locationZone;
    }

    public void setLocationZone(String locationZone) {
        this.locationZone = locationZone;
    }

    public Double getPriceEffectivePrice() {
        return priceEffectivePrice;
    }

    public void setPriceEffectivePrice(Double priceEffectivePrice) {
        this.priceEffectivePrice = priceEffectivePrice;
    }

    public Double getPricePricingUnitQuantity() {
        return pricePricingUnitQuantity;
    }

    public void setPricePricingUnitQuantity(Double pricePricingUnitQuantity) {
        this.pricePricingUnitQuantity = pricePricingUnitQuantity;
    }

    public Double getPriceTierStartAmount() {
        return priceTierStartAmount;
    }

    public void setPriceTierStartAmount(Double priceTierStartAmount) {
        this.priceTierStartAmount = priceTierStartAmount;
    }

    public String getPriceUnit() {
        return priceUnit;
    }

    public void setPriceUnit(String priceUnit) {
        this.priceUnit = priceUnit;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getResourceGlobalName() {
        return resourceGlobalName;
    }

    public void setResourceGlobalName(String resourceGlobalName) {
        this.resourceGlobalName = resourceGlobalName;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getSkuDescription() {
        return skuDescription;
    }

    public void setSkuDescription(String skuDescription) {
        this.skuDescription = skuDescription;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getSystemLabelsKey() {
        return systemLabelsKey;
    }

    public void setSystemLabelsKey(String systemLabelsKey) {
        this.systemLabelsKey = systemLabelsKey;
    }

    public String getSystemLabelsValue() {
        return systemLabelsValue;
    }

    public void setSystemLabelsValue(String systemLabelsValue) {
        this.systemLabelsValue = systemLabelsValue;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Long getUsageAmount() {
        return usageAmount;
    }

    public void setUsageAmount(Long usageAmount) {
        this.usageAmount = usageAmount;
    }

    public Double getUsageAmountInPricingUnits() {
        return usageAmountInPricingUnits;
    }

    public void setUsageAmountInPricingUnits(Double usageAmountInPricingUnits) {
        this.usageAmountInPricingUnits = usageAmountInPricingUnits;
    }

    public String getUsagePricingUnit() {
        return usagePricingUnit;
    }

    public void setUsagePricingUnit(String usagePricingUnit) {
        this.usagePricingUnit = usagePricingUnit;
    }

    public String getUsageUnit() {
        return usageUnit;
    }

    public void setUsageUnit(String usageUnit) {
        this.usageUnit = usageUnit;
    }

    public String getUsageEndTime() {
        return usageEndTime;
    }

    public void setUsageEndTime(String usageEndTime) {
        this.usageEndTime = usageEndTime;
    }

    public String getUsageStartTime() {
        return usageStartTime;
    }

    public void setUsageStartTime(String usageStartTime) {
        this.usageStartTime = usageStartTime;
    }
}
