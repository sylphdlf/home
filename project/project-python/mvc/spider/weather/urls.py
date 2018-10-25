from django.urls import path

from . import views

urlpatterns = [
    path('', views.index, name='index'),
    path('getWeather/', views.get_weather, name='getWeather'),
]
