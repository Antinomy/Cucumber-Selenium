Feature: 网页搜索

  Scenario: 在百度里搜索谷歌
    Given 打开浏览器到baidu.com
    And 输入 "google"
    When 点击百度一下
    Then 见到搜索结果