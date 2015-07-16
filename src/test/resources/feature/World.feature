Feature: 运行环境

  Scenario: 本地环境
    Given 在本地环境
    Then 关闭浏览器

  Scenario: 远程环境
    Given 在目标环境 "local_any_firefox"
    Then 退出浏览器


