from django.db import models

# Create your models here.
from django.db import models


class Weather(models.Model):
    id = models.BigIntegerField(primary_key=True)
    country = models.CharField(max_length=20, null=False, rel='国家')
    province = models.CharField(max_length=20, null=True)
    city = models.CharField(max_length=20, null=False)
    weather = models.CharField(max_length=20, null=False)
    temperature = models.IntegerField(null=False)
    humidity = models.IntegerField(null=False)
    wind = models.CharField(max_length=20, null=False)
    data_update_time = models.DateTimeField(auto_now=False, auto_now_add=False, null=True)
    create_time = models.DateTimeField(auto_now_add=True, null=False)
    remark = models.CharField(max_length=200, null=True)
    is_deleted = models.BooleanField(default=0, null=True)

    class Meta:
        db_table = 'p_weather'



