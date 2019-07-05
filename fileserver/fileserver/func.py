def upload(request):
	name = request.GET.get("name")
	file = request.FILE.get("file")
	obj = FILES.objects.create(name=name, file=file);
	obj.save()
	return JsonResponse({
        'error':0,
    })
	
def download(request):
	name = request.GET.get('name')
	obj = FILES.objects.get(name=name)
    return HttpResponse(obj.file)
	